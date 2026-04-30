package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public static class CreateReviewDTO {
        public Long marketId;
        public Long regionId;
        public Integer stars;
        public String content;
    }
}