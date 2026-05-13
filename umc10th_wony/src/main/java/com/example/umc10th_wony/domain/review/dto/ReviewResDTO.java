package com.example.umc10th_wony.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResDTO {

    private Long reviewId;
    private String content;
    private Integer score;

    @Builder
    public record MyReviewResponse (
            Long reviewId,
            Long missionId,
            String storeName,
            Integer score,
            String content,
            LocalDateTime createdAt
    ) {}
}
