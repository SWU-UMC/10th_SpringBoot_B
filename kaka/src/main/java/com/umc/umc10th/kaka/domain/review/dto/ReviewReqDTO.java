package com.umc.umc10th.kaka.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReviewReq(
            Float stars,
            String content
    ) {}
}
