package com.umc.umc10th.kaka.domain.mission.dto;

import java.util.List;

public class MissionResDTO {

    public record MissionList(
            Long missionId,
            String marketName,
            Integer point,
            String status
    ) {}

    public record MissionPage(
            List<MissionList> content,
            int page,
            int size,
            boolean hasNext
    ) {}

    public record CompleteMissionRes(
            Long missionId,
            String message
    ) {}
}
