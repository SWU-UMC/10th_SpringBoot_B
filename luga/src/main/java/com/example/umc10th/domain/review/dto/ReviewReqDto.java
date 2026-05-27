package com.example.umc10th.domain.review.dto;

public class ReviewReqDto {

    public record CreateReviewReqDto(
            String body,
            Integer grade
    ) {}
}
