package com.example.umc10th.domain.mission.dto;

import lombok.*;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionDTO {
        public Long missionId;
        public String marketName;
        public String content;
        public Integer point;
        public String status;
    }

    public static class GetMissionDTO {
        public Long missionId;
        public Integer point;
        public String status;
    }

    public static class CompleteDTO {
        public Long missionId;
        public String message;
    }
}
