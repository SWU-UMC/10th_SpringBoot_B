package com.umc.umc10th.kaka.domain.review.converter;

import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewReq dto) {
        return Review.builder()
                .marketId(dto.marketId())
                .regionId(dto.regionId())
                .stars(dto.stars())
                .content(dto.content())
                .build();
    }

    public static ReviewResDTO.CreateReviewRes toCreateReview(Review review) {
        return new ReviewResDTO.CreateReviewRes();
    }
}
