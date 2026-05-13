package com.umc.umc10th.kaka.domain.review.dto;


import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    public record CreateReviewRes(
            Long reviewId,
            Float stars,
            String content,
            String replyContent,
            String createdAt
    ) {
    }

    @Builder
    public record ReviewItem(
            Long reviewId,
            String memberName,
            Float stars,
            String content,
            String replyContent,
            String createdAt
    ) {}

    @Builder
    public record ReviewPage(
            List<ReviewItem> content,
            String nextCursor,
            Integer pageSize,
            Boolean hasNext
    ) {}
}