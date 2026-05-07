package com.example.umc10th.domain.user.dto;

import lombok.Getter;

public class UserReqDto {

    @Getter
    public static class SignupReqDto {
        private String name;
        private String email;
        private String password;
        private String phone;
        private String address;
    }
}
