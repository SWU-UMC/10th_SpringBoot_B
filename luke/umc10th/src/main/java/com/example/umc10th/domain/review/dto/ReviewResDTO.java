package com.example.umc10th.domain.review.dto;

import lombok.*;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private Long reviewId;
        private String message;
    }
}