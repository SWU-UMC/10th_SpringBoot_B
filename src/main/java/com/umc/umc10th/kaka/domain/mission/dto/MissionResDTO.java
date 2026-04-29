package com.umc.umc10th.kaka.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {
    @Getter
    @Builder
    public static class MissionListClass {
        private Long missionId;
        private String marketName;
        private Integer point;
        private String status;
    }

    // 미션 목록 페이지
    @Getter
    @Builder
    public static class MissionPageClass {
        private List<MissionListClass> content;
        private int page;
        private int size;
        private boolean hasNext;
    }

    // 미션 완료
    @Getter
    @Builder
    public static class CompleteMissionResClass {
        private Long missionId;
        private String message;
    }
}
