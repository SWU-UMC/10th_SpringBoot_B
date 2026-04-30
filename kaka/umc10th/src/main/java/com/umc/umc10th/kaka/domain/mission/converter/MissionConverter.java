package com.umc.umc10th.kaka.domain.mission.converter;

import com.umc.umc10th.kaka.domain.mission.dto.MissionResDTO;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionListClass toMissionList(Mission mission) {
        return MissionResDTO.MissionListClass.builder()
                .missionId(mission.getId())
                .marketName(mission.getStore().getName())
                .point(mission.getPoint())
                .status(mission.getStatus())
                .build();
    }

    public static MissionResDTO.MissionPageClass toMissionPage(
            List<Mission> missions, int page, int size, boolean hasNext
    ) {
        return MissionResDTO.MissionPageClass.builder()
                .content(missions.stream()
                        .map(MissionConverter::toMissionList)
                        .collect(Collectors.toList()))
                .page(page)
                .size(size)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.CompleteMissionResClass toCompleteMission(Mission mission) {
        return MissionResDTO.CompleteMissionResClass.builder()
                .missionId(mission.getId())
                .message("미션이 정상적으로 완료되었습니다.")
                .build();
    }
}
