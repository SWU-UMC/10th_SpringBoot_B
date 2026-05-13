package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    public record CreateReviewResDto (
            Long reviewId,
            LocalDateTime createdAt
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
}
