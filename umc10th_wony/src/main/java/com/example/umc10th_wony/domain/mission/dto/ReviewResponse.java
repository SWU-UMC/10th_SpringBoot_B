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
public class ReviewResponse {

    private Long reviewId;
    private Long missionId;
    private String missionTitle;
    private Long memberId;
    private String memberNickname;
    private int rating;
    private String content;
    private LocalDateTime createdAt;
}
