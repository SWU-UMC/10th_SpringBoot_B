package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMission(
            String content,
            LocalDate deadline,
            Integer point,
            MissionStatus status
    ){}

    public static class CompleteDTO {
        public Long missionId;
    }

}
