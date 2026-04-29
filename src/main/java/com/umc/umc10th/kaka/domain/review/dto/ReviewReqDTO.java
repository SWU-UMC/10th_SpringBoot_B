package com.umc.umc10th.kaka.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewReqClass {
        private Integer marketId;
        private Integer regionId;
        private Float stars;
        private String content;
    }
}
