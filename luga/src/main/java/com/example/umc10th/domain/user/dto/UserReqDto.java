package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialLogin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserReqDto {

    public record SignupReqDto (
            @NotBlank String name,
            @NotBlank @Email String email,
            @NotBlank @Size(min = 0, message = "비밀번호는 8자 이상이어야 합니다.") String password,
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
