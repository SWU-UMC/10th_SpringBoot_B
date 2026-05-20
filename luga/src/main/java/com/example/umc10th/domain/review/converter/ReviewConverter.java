package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.dto.CursorPageResDto;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDto.CreateReviewResDto toCreateReviewResDto(Review review) {
        return new ReviewResDto.CreateReviewResDto(
                review.getId(),
                review.getDate()
        );
    }

    public static ReviewResDto.ReviewDto toReviewDto(Review review) {
        return new ReviewResDto.ReviewDto(
                review.getId(),
                review.getGrade(),
                review.getBody(),
                review.getRestaurant().getName()
        );
    }

    public static ReviewResDto.ReviewListResDto toReviewListResDto(Slice<Review> slice) {
        List<ReviewResDto.ReviewDto> reviews = slice.getContent().stream()
                .map(ReviewConverter::toReviewDto)
                .toList();

        Review lastReview = slice.hasNext()
                ? slice.getContent().get(slice.getContent().size() - 1)
                : null;

        return new ReviewResDto.ReviewListResDto(
                reviews,
                lastReview != null ? lastReview.getId() : null,
                lastReview != null ? lastReview.getGrade() : null,
                slice.hasNext()
        );
    }
}
