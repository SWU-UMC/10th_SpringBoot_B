package com.example.umc10th_wony.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponse {

    private Long missionId;
    private String missionTitle;
    private String description;
    private String region;       // 지역명
    private int reward;          // 보상 포인트
    private String status;       // OPEN / IN_PROGRESS / COMPLETED
    private LocalDate deadline;
    private LocalDate createdAt;

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer reward,
            String conditional,
            LocalDate deadline
    ) {}
}
