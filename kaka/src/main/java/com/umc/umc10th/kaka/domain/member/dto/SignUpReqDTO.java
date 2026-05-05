package com.umc.umc10th.kaka.domain.member.dto;


public class SignUpReqDTO {

    public record SignUpReqBody(
            String name,
            String email,
            String password,
            String phoneNumber,
            String gender,
            String birth,
            String address,
            Integer agreedId
    ) {}
}
