package com.umc.umc10th.kaka.domain.review.converter;

import com.umc.umc10th.kaka.domain.mission.entity.Store;
import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(Store store, ReviewReqDTO.CreateReviewReq dto) {
        return Review.builder()
                .store(store)
                .stars(dto.stars())
                .content(dto.content())
                .build();
    }

    public static ReviewResDTO.CreateReviewRes toCreateReview(Review review) {
        return new ReviewResDTO.CreateReviewRes(
                review.getId(),
                review.getStars(),
                review.getContent()
        );
    }
}
