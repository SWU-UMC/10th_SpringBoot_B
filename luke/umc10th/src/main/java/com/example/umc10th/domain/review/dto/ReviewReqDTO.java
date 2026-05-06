package com.example.umc10th.domain.review.dto;

import lombok.*;

public class ReviewReqDTO {

    @Getter @Setter
    public static class CreateReviewDTO {
        private Integer stars;
        private String content;
    }
}