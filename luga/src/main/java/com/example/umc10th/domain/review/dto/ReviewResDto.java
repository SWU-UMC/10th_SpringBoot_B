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
            Integer stars,
            String content,
            String restaurantName
    ) {}

    public record ReviewListResDto(
            List<ReviewDto> content,
            Long nextCursorID,
            Integer nextCursorGrade,
            boolean hasNext
    ) {}
}
