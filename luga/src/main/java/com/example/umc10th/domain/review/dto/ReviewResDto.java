package com.example.umc10th.domain.review.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    public record CreateReviewResDto (
            Long reviewId,
            LocalDate date
    ) {}

    public record ReviewDto(
            Long reviewId,
            Long userId,
            Long restaurantId,
            Long regionID,
            String body,
            Integer grade,
            LocalDate date
    ) {}

    public record ReviewListResDto(
            List<ReviewDto> reviews,
            boolean hasNext
    ) {}
}
