package com.example.umc10th_wony.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResponse {

    private Long memberId;
    private String email;
    private String nickname;
    private int completedMissionCount; // 완료한 미션 수
    private int writtenReviewCount;    // 작성한 리뷰 수
    private LocalDateTime createdAt;
}
