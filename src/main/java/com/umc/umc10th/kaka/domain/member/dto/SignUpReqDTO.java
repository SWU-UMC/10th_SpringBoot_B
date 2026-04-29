package com.umc.umc10th.kaka.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class SignUpReqDTO {

    @Getter
    @Builder
    public static class SignUpReqBodyClass {
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String gender;
        private String birth;
        private String address;
        private Integer agreedId;

    }
}
