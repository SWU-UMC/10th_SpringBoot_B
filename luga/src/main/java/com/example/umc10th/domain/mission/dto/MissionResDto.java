package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDto {

    public record MissionDto (
        Long missionId,
        String content,
        String point,
        LocalDateTime endDate
    ) {}

    // 진행 중인 미션 조회용
    public record MissionProgressDto(
            Long missionId,
            String content,
            Integer point,
            LocalDateTime endDate
    ) {}

    // 성공 처리 결과
    public record MissionSuccessResDto (
            Long missionId,
            LocalDateTime completedAt
    ) {}

    // 미션 생성 결과
    public record CreateMissionResDto (
            Long missionId,
            LocalDateTime createdAt
    ) {}

    // 미션 참여 결과
    public record JoinMissionResDto(
            Long userMissionId,
            Long missionId,
            MissionStatus status
    ) {}
}
