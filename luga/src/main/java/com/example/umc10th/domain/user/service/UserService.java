package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResDto.SignupResDto signup(UserReqDto.SignupReqDto request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new GeneralException(UserErrorCode.MEMBER_ALREADY_EXISTS);
        }

        User user = UserConverter.toUser(request);
        User saved = userRepository.save(user);

        return UserConverter.toSignupResDto(saved);
    }

    @Transactional
    public UserResDto.AddFoodPreferenceResDto addFoodPreference(
            Long userId, UserReqDto.AddFoodPreferenceReqDto request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.MEMBER_NOT_FOUND));

        // 선호 음식 저장 로직 (FoodPreference 엔티티/레포지토리 연동 필요)

        return UserConverter.toAddFoodPreferenceResDto(userId, request.foodTypes());
    }
}
