package com.umc.umc10th.kaka.domain.home.dto;

import lombok.Getter;

public class HomeRegionMissionReqDTO {

    @Getter
    public static class RegionMissionReqClass {
        private String token; // 임시 (나중에 JWT)
    }
}
