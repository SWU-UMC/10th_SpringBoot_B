package com.example.umc10th_wony.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionCreateRequest {

    @NotBlank
    private String missionTitle;

    @NotBlank
    private String description;

    @NotBlank
    private String conditional;

    @NotNull
    private Integer reward;

    @NotNull
    private LocalDate deadline;
}