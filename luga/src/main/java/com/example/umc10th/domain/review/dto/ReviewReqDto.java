package com.example.umc10th.domain.review.dto;

import lombok.Getter;

public class ReviewReqDto {

    @Getter
    public static class CreateReviewReqDto {
        private Long restaurantId;
        private Float score;
        private String body;
    }
}
