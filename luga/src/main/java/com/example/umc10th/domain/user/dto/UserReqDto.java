package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialLogin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class UserReqDto {

    public record LoginReqDto(
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}

    public record SignupReqDto (
            @NotBlank String name,
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.") String password,
            String phone,
            String address,
            String addressDetail,
            Gender gender,
            LocalDate birth,
            SocialLogin socialLogin,

            // 약관 동의 추가
            @NotNull AgreementReqDto agreement,

            // 선호 음식 리스트 추가
            List<Long> foodTypes

    ) {}

    public record AgreementReqDto(
            @NotNull Integer agreeAge14,
            @NotNull Integer agreeService,
            @NotNull Integer agreePrivate,
            Integer agreeLocation,
            Integer agreeMarketing,
            Integer agreeEventAlarm,
            Integer agreeReviewAlarm,
            Integer agreeQaAlarm
    ) {}

    // 선호 음식 등록
    public record AddFoodPreferenceReqDto(
            java.util.List<Long> foodTypes
    ) {}
}
