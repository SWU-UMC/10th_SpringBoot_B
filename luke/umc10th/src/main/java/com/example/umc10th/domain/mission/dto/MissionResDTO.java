package com.example.umc10th.domain.mission.dto;

import lombok.*;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionListDTO {
        public List<MissionDTO> content;
        public Integer page;
        public Integer size;
        public Boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionDTO {
        public Long missionId;
        public String marketName;
        public Integer point;
        public String status;
    }

    public static class CompleteDTO {
        public Long missionId;
        public String message;
    }
}
