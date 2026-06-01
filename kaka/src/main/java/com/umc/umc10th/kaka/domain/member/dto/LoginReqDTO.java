package com.umc.umc10th.kaka.domain.member.dto;

public class LoginReqDTO {
    public record LoginReqBody(
            String email,
            String password
    ) {}
}
