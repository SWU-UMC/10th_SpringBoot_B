package com.umc.umc10th.kaka.domain.review.controller;  // ← 세미콜론!

import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.exception.code.ReviewSuccessCode;
import com.umc.umc10th.kaka.domain.review.service.ReviewService;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/v1/create")
    public ApiResponse<ReviewResDTO.CreateReviewResClass> getCreateReview(
            @RequestBody ReviewReqDTO.CreateReviewReqClass dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.createReview(dto));
    }
}