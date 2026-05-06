package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/markets/{marketId}")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable Long marketId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        Long memberId = 1L; // memberId 임시값

        ReviewResDTO.CreateReviewDTO response =
                reviewService.createReview(memberId, marketId, request);

        return ApiResponse.onSuccess(response);
    }
}
