package com.umc.umc10th.kaka.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class SignUpResDTO {

    @Getter
    @Builder
    public static class SignUpResBodyClass {
        private Long id;
        private String refreshToken;
    }
}
