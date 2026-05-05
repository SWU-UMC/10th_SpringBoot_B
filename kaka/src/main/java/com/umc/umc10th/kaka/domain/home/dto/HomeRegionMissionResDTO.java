package com.umc.umc10th.kaka.domain.home.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class HomeRegionMissionResDTO {

    @Getter
    @Builder
    public static class MissionListClass {
        private Long missionId;
        private String marketName;
        private Integer point;
        private String content;
    }

    @Getter
    @Builder
    public static class MissionPageClass {
        private List<MissionListClass> content;
        private int page;
        private int size;
        private boolean hasNext;
    }
}
