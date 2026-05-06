package com.example.umc10th_wony.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewReqDTO {

    private Long memberId;
    private Long missionId;
    private String content;
    private Integer score;
}
