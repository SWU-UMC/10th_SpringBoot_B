package com.example.umc10th_wony.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionCompleteResponse {

    private Long missionId;
    private Long memberId;
    private String status;          // COMPLETED
    private int rewardPoint;        // 지급된 보상 포인트
    private LocalDateTime completedAt;
}
