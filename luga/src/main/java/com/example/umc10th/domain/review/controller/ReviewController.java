package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/{restaurantId}/reviews/{userId}")
    public ApiResponse<ReviewResDto.CreateReviewResDto> createReview(
            @PathVariable Long restaurantId,
            @PathVariable Long userId,
            @RequestBody @Valid ReviewReqDto.CreateReviewReqDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, reviewService.createReview(restaurantId, userId, request));
    }

    @Operation(
            summary = "내 리뷰 목록 조회",
            description = "ID순 또는 별점순 정렬",
            parameters = {
                    @Parameter(name = "memberId", description = "사용자 ID", example = "1", required = true),
                    @Parameter(name = "sort", description = "정렬 기준 (id | stars)", example = "id", required = true),
                    @Parameter(name = "cursor", description = "마지막으로 본 커서값 (첫 요청 시 생략)"),
                    @Parameter(name = "cursorGrade", description = "마지막으로 본 리뷰 별점 (stars 정렬 시 cursor와 함께 필요)"),
                    @Parameter(name = "size", description = "페이지 크기", example = "3")
            }
    )
    @GetMapping("/my")
    public ApiResponse<ReviewResDto.ReviewListResDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "id") String sort, // 기본값 = id
            @RequestParam(required = false) Long cursor,        // ID순 커서
            @RequestParam(required = false) Integer cursorGrade, // 별점순 커서 (stars 정렬 시)
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(
                reviewService.getMyReviews(memberId, sort, cursor, cursorGrade, size));
    }
}
