package com.umc.umc10th.kaka.domain.review.dto;


public class ReviewResDTO {

    public record CreateReviewRes(
            Long reviewId,
            Float stars,
            String content
    ) {}
}