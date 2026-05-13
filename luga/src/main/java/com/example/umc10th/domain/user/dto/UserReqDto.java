package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialLogin;

import java.time.LocalDate;

public class UserReqDto {

    public record SignupReqDto (
        String name,
        String email,
        String phone,
        String address,
        String addressDetail,
        Gender gender,
        LocalDate birth,
        SocialLogin socialLogin
    ) {}

    // 선호 음식 등록
    public record AddFoodPreferenceReqDto(
            java.util.List<Long> foodTypes
    ) {}
}
