package com.umc.umc10th.kaka.domain.member.dto;

public class SignUpResDTO {

    public record SignUpResBody(
            Long id,
            String refreshToken
    ) {}
}
