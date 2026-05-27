package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.entity.Agreement;
import com.example.umc10th.domain.user.entity.FoodType;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.FoodPreference;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.AgreementRepository;
import com.example.umc10th.domain.user.repository.FoodPreferenceRepository;
import com.example.umc10th.domain.user.repository.FoodTypeRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AgreementRepository agreementRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final FoodPreferenceRepository foodPreferenceRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResDto.SignupResDto signup(UserReqDto.SignupReqDto request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new GeneralException(UserErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 약관 동의 저장
        Agreement agreement = UserConverter.toAgreement(request.agreement());
        agreementRepository.save(agreement);

        // 유저 저장
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = UserConverter.toUser(request, encodedPassword, agreement);
        User saved = userRepository.save(user);

        // 음식 선호 저장
        List<Long> foodTypes = request.foodTypes();
        if (foodTypes != null && !foodTypes.isEmpty()) {
            List<FoodPreference> preferences = foodTypes.stream()
                    .flatMap(foodTypeId -> foodTypeRepository.findById(foodTypeId)
                            .map(foodType -> FoodPreference.builder()
                                    .user(saved)
                                    .foodType(foodType)
                                    .build())
                            .stream())
                    .toList();
            foodPreferenceRepository.saveAll(preferences);
        }

        return UserConverter.toSignupResDto(saved);
    }

    @Transactional
    public UserResDto.AddFoodPreferenceResDto addFoodPreference(
            Long userId, UserReqDto.AddFoodPreferenceReqDto request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.MEMBER_NOT_FOUND));

        List<FoodPreference> preferences = request.foodTypes().stream()
                .map(foodTypeId -> {
                    FoodType foodType = foodTypeRepository.findById(foodTypeId)
                            .orElseThrow(() -> new GeneralException(UserErrorCode.FOOD_TYPE_NOT_FOUND));
                    return FoodPreference.builder()
                            .user(user)
                            .foodType(foodType)
                            .build();
                })
                .toList();
        foodPreferenceRepository.saveAll(preferences);

        return UserConverter.toAddFoodPreferenceResDto(userId, request.foodTypes());
    }
}
