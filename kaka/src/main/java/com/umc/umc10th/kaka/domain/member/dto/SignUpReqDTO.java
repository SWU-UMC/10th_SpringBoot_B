package com.umc.umc10th.kaka.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SignUpReqDTO {

    public record SignUpReqBody(
            @NotBlank
            String name,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String password,

            @NotBlank
            String phoneNumber,

            @NotBlank
            @Pattern(regexp = "MALE|FEMALE")
            String gender,

            @NotBlank
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
            String birth,

            @NotBlank
            @Pattern(regexp = "SEOUL")
            String address,

            @NotNull
            Integer agreedId
    ) {}
}
