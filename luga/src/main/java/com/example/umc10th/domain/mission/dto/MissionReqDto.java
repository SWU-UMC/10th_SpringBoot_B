package com.example.umc10th.domain.mission.dto;

import java.time.LocalDateTime;

public class MissionReqDto {

    // 미션 생성
    public record CreateMissionReqDto(
            Long restaurantId,
            Long regionId,
            String body,
            String award,
            LocalDateTime dDay
    ) {}

    // 미션 도전
    public record JoinMissionReqDto(
            Long missionId
    ) {}
}
