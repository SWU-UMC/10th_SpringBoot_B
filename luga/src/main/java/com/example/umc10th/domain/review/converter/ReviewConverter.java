package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDto.CreateReviewResDto toCreateReivewResDto(Review review) {
        return new ReviewResDto.CreateReviewResDto(
                review.getId(),
                review.getDate()
        );
    }

    public static ReviewResDto.ReviewDto toReviewDto(Review review) {
        return new ReviewResDto.ReviewDto(
                review.getId(),
                review.getUser().getId(),
                review.getRestaurant().getId(),
                review.getRegion().getId(),
                review.getBody(),
                review.getGrade(),
                review.getDate()
        );
    }

    public static ReviewResDto.ReviewListResDto toReviewListResDto(Slice<Review> slice) {
        List<ReviewResDto.ReviewDto> reviews = slice.getContent().stream()
                .map(ReviewConverter::toReviewDto)
                .toList();

        return new ReviewResDto.ReviewListResDto(reviews, slice.hasNext());
    }
}
