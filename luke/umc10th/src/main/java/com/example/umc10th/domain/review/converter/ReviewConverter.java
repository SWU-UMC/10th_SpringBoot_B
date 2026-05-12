package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Market;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(
            Member member,
            Market market,
            ReviewReqDTO.CreateReviewDTO request
    ) {

        return Review.builder()
                .member(member)
                .market(market)
                .stars(request.getStars())
                .content(request.getContent())
                .build();
    }

    public static ReviewResDTO.CreateReviewDTO toCreateReviewDTO(
            Review review
    ) {

        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .message("리뷰 작성 완료!")
                .build();
    }
}
