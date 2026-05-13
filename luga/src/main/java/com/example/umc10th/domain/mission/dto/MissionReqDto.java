package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

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

    // 진행 중인 미션 조회
    public record InProgressMissionReqDto(
            @NotNull(message = "사용자 Id는 필수!")
            Long userId
    ) {}
}
