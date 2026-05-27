package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.entity.Agreement;
import com.example.umc10th.domain.user.entity.User;

import java.util.List;

public class UserConverter {

    public static Agreement toAgreement(UserReqDto.AgreementReqDto dto) {
        return Agreement.builder()
                .agreeAge14(dto.agreeAge14())
                .agreeService(dto.agreeService())
                .agreePrivate(dto.agreePrivate())
                .agreeLocation(dto.agreeLocation() != null ? dto.agreeLocation() : 0)
                .agreeMarketing(dto.agreeMarketing() != null ? dto.agreeMarketing() : 0)
                .agreeEventAlarm(dto.agreeEventAlarm() != null ? dto.agreeEventAlarm() : 0)
                .agreeReviewAlarm(dto.agreeReviewAlarm() != null ? dto.agreeReviewAlarm() : 0)
                .agreeQaAlarm(dto.agreeQaAlarm() != null ? dto.agreeQaAlarm() : 0)
                .build();
    }

    public static User toUser(UserReqDto.SignupReqDto request, String encodedPassword, Agreement agreement) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .phone(request.phone())
                .address(request.address())
                .addressDetail(request.addressDetail())
                .gender(request.gender())
                .birth(request.birth())
                .socialLogin(request.socialLogin())
                .agreement(agreement)
                .build();
    }

    public static UserResDto.SignupResDto toSignupResDto(User user) {
        return new UserResDto.SignupResDto(
                user.getId(),
                user.getCreatedAt()
        );
    }

    public static UserResDto.AddFoodPreferenceResDto toAddFoodPreferenceResDto(Long userId, List<Long> foodTypes) {
        return new UserResDto.AddFoodPreferenceResDto(userId, foodTypes);
    }
}
