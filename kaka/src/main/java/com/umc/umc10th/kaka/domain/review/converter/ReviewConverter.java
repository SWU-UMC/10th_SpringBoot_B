package com.umc.umc10th.kaka.domain.review.converter;

import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.entity.Review;
import com.umc.umc10th.kaka.domain.store.entity.Store;
import org.springframework.data.domain.Slice;

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
                review.getContent(),
                review.getReply() != null ? review.getReply().getContent() : null,
                review.getCreatedAt().toString()
        );
    }

    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        return ReviewResDTO.ReviewItem.builder()
                .reviewId(review.getId())
                .memberName(review.getMember().getName())
                .stars(review.getStars())
                .content(review.getContent())
                .replyContent(review.getReply() != null ? review.getReply().getContent() : null)
                .createdAt(review.getCreatedAt().toString())
                .build();
    }

    public static ReviewResDTO.ReviewPage toReviewPage(
            Slice<Review> slice,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.ReviewPage.builder()
                .content(slice.getContent().stream()
                        .map(ReviewConverter::toReviewItem)
                        .toList())
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .hasNext(slice.hasNext())
                .build();
    }
}
