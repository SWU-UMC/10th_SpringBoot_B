package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "리뷰 작성",
            description = "식당 ID를 경로 변수로 받아 해당 식당에 리뷰 작성",
            parameters = @Parameter(name = "restaurantId", description = "리뷰를 작성할 식당의 Id", example = "1")
    )
    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<ReviewResDto.CreateReviewResDto> createReview(
            @PathVariable Long restaurantId,
            @RequestBody ReviewReqDto.CreateReviewReqDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, reviewService.createReview(restaurantId, request));
    }
}
