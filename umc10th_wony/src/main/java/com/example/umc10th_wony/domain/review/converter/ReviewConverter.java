package com.example.umc10th_wony.domain.review.converter;

import com.example.umc10th_wony.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.review.dto.ReviewResDTO;
import com.example.umc10th_wony.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toEntity(ReviewReqDTO dto, Member member, Mission mission) {
        return Review.builder()
                .content(dto.getContent())
                .score(dto.getScore())
                .member(member)
                .mission(mission)
                .build();
    }

    public static ReviewResDTO toResponse(Review review) {
        return ReviewResDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }

    public static ReviewResDTO.MyReviewResponse toMyReviewResponse(Review review) {
        return ReviewResDTO.MyReviewResponse.builder()
                .reviewId(review.getId())
                .missionId(review.getMission().getId())
                .storeName(review.getMission().getStore().getName())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
