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
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {

        ReviewResDTO.CreateReviewDTO response =
                reviewService.createReview(memberId, marketId, request);

        return ApiResponse.onSuccess(response);
    }
}
