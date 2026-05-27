package com.example.umc10th.domain.user.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserResDto {

    public record LoginResDto(
            Long userId,
            String accessToken
    ) {}

    public record SignupResDto (
            Long userId,
            LocalDateTime createdAt
    ) {}

    // 마이페이지 프로필
    public record MyPageResDto(
            Long userId,
            String name,
            String email,
            String phone,
            String address,
            Integer point
    ) {}

    // 선호 음식 등록 결과
    public record AddFoodPreferenceResDto(
            Long userId,
            List<Long> foodTypes
    ) {}
}
