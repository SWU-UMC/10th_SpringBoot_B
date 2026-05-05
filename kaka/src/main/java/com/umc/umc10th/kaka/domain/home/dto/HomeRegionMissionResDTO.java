package com.umc.umc10th.kaka.domain.home.dto;

import java.util.List;

public class HomeRegionMissionResDTO {

    public record MissionList(
            Long missionId,
            String marketName,
            Integer point,
            String content
    ) {}

    public record MissionPage(
            List<MissionList> content,
            int page,
            int size,
            boolean hasNext
    ) {}
}
