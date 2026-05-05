package com.umc.umc10th.kaka.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    public record CreateReviewReq(
            Integer marketId,
            Integer regionId,
            Float stars,
            String content
    ) {}
}
