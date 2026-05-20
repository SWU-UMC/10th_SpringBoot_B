package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.entity.User;

import java.util.List;

public class UserConverter {

    public static User toUser(UserReqDto.SignupReqDto request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .addressDetail(request.addressDetail())
                .gender(request.gender())
                .birth(request.birth())
                .socialLogin(request.socialLogin())
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
