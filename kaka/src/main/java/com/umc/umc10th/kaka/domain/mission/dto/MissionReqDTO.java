package com.umc.umc10th.kaka.domain.mission.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CompleteMissionReq(
            @NotNull
            Long missionId
    ) {}

    public record CreateMission(

            @NotNull
            @Future
            LocalDate deadline,

            @NotNull
            @Min(value = 1)
            @Max(value=100000)
            Integer point,

            @NotBlank
            @Size(max = 255)
            String conditional
    ){}
}
