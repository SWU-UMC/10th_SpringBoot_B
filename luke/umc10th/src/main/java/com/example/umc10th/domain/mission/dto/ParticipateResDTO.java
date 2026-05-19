package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ParticipateResDTO {

    @Builder
    public record MyMissionPreviewDTO(

            Long missionId,
            String content,
            Integer point,
            LocalDateTime endDate

    ) {
    }
}
