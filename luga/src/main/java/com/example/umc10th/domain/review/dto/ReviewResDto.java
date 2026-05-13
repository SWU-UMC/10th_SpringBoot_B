package com.example.umc10th.domain.review.dto;

import java.time.LocalDate;
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
            Long regionId,
            String body,
            Integer grade,
            LocalDate date
    ) {}
}
