package com.umc.umc10th.kaka.domain.mission.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CompleteMissionReq(
            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {
    }

    public record CreateMission(

            @NotNull(message = "마감일은 필수입니다.")
            @Future(message = "마감일은 오늘 이후여야 합니다.")
            LocalDate deadline,

            @NotNull(message = "포인트는 필수입니다.")
            @Min(value = 1, message = "포인트는 최소 1 이상이어야 합니다.")
            @Max(value = 100000, message = "포인트는 최대 100,000 이하여야 합니다.")
            Integer point,

            @NotBlank(message = "미션 조건은 필수입니다.")
            @Size(max = 255, message = "미션 조건은 255자 이하여야 합니다.")
            String conditional
    ) {
    }
}
