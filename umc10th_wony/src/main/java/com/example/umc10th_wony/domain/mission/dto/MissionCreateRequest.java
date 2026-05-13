package com.example.umc10th_wony.domain.mission.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionCreateRequest {

    @NotBlank(message = "미션 제목은 필수입니다.")
    @Size(max = 30, message = "미션 제목은 30자 이하로 입력해주세요.")
    private String missionTitle;

    @NotBlank(message = "미션 설명은 필수입니다.")
    @Size(max = 200, message = "미션 설명은 200자 이하로 입력해주세요.")
    private String description;

    @NotBlank(message = "미션 조건은 필수입니다.")
    @Size(max = 100, message = "미션 조건은 100자 이하로 입력해주세요.")
    private String conditional;

    @NotNull(message = "보상 포인트는 필수입니다.")
    @Min(value = 0, message = "보상 포인트는 0 이상이어야 합니다.")
    private Integer reward;

    @NotNull(message = "마감일은 필수입니다.")
    @Future(message = "마감일은 현재보다 이후 날짜여야 합니다.")
    private LocalDate deadline;
}