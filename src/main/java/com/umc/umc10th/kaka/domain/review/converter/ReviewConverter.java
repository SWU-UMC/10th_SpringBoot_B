package com.umc.umc10th.kaka.domain.review.converter;

import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewReqClass dto) {
        return Review.builder()
                .marketId(dto.getMarketId())
                .regionId(dto.getRegionId())
                .stars(dto.getStars())
                .content(dto.getContent())
                .build();
    }

    public static ReviewResDTO.CreateReviewResClass toCreateReview(Review review) {
        return ReviewResDTO.CreateReviewResClass.builder()
                .build();
    }
}
