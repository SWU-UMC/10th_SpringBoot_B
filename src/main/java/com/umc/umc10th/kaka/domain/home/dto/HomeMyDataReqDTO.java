package com.umc.umc10th.kaka.domain.home.dto;

import lombok.Getter;

public class HomeMyDataReqDTO {
    @Getter
    public static class MyDataReqClass {
        private String token; // 임시 (나중에 JWT)
    }
}
