package com.example.umc10th_wony.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    public static class Login {
        private String accessToken;
        private String tokenType;
    }
}