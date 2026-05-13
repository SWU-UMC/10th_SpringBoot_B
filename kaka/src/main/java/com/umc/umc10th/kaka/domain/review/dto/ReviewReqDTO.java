package com.umc.umc10th.kaka.domain.review.dto;

import com.umc.umc10th.kaka.global.validation.annotation.ValidStars;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReviewReqDTO {

    public record CreateReviewReq(

            @ValidStars
            Float stars,

            @NotBlank
            @Size(min = 30, max = 300)
            String content
    ) {}
}
