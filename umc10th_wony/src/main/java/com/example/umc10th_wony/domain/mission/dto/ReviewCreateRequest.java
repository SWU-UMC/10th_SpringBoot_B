package com.example.umc10th_wony.domain.mission.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCreateRequest {

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;

    @NotNull(message = "별점은 필수입니다.")
    @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
    private Integer rating;

    @NotBlank(message = "리뷰 내용은 필수입니다.")
    @Size(min = 5, max = 500, message = "리뷰 내용은 5자 이상 500자 이하여야 합니다.")
    private String content;
}
