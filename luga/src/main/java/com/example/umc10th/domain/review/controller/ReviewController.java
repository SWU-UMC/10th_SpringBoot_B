package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping
    public ApiResponse<ReviewResDto.CreateReviewResDto> createReview(
            @RequestBody ReviewReqDto.CreateReviewReqDto request) {
        ReviewResDto.CreateReviewResDto result = ReviewResDto.CreateReviewResDto.builder()
                .reviewId(1L)
                .createdAt(LocalDateTime.now())
                .build();
        return ApiResponse.onSuccess(SuccessStatus.CREATED, result);
    }
}
