package com.example.umc10th_wony.domain.review.controller;

import com.example.umc10th_wony.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_wony.domain.review.dto.ReviewResDTO;
import com.example.umc10th_wony.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th_wony.global.apiPayload.ApiResponse;
import com.example.umc10th_wony.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResDTO> createReview(@RequestBody ReviewReqDTO request) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(request));
    }
}
