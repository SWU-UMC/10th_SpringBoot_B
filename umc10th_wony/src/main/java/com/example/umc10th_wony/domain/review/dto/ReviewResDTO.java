package com.example.umc10th_wony.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResDTO {

    private Long reviewId;
    private String content;
    private Integer score;
}
