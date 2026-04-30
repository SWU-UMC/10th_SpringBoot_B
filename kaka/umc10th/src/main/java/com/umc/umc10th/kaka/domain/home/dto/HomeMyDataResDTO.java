package com.umc.umc10th.kaka.domain.home.dto;

import lombok.Builder;
import lombok.Getter;

public class HomeMyDataResDTO {

    @Getter
    @Builder
    public static class MyDataResClass {
        private String nickname;
        private String email;
        private String phoneNumber;
        private Integer phoneNumberStatus;
        private Integer userPoint;
    }
}
