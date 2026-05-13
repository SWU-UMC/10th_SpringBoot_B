package com.umc.umc10th.kaka.domain.review.dto;

import com.umc.umc10th.kaka.global.validation.annotation.ValidStars;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewReqDTO {

    public record CreateReviewReq(

            @ValidStars(message = "별점은 0.5 단위로 0.5~5.0 사이여야 합니다.")
            Float stars,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            @Size(min = 30, max = 300, message = "리뷰 내용은 30자 이상 300자 이하여야 합니다.")
            String content
    ) {
    }

    // 내가 작성한 리뷰 조회 요청
    public record GetMyReviewsReq(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            @NotNull(message = "페이지 사이즈는 필수입니다.")
            Integer pageSize,

            String cursor,

            @NotNull(message = "정렬 기준은 필수입니다.")
            String query
    ) {}
}
