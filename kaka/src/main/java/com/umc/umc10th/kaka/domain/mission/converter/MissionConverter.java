package com.umc.umc10th.kaka.domain.mission.converter;

import com.umc.umc10th.kaka.domain.mission.dto.MissionResDTO;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionList toMissionList(Mission mission) {
        return new MissionResDTO.MissionList(
                mission.getId(),
                mission.getStore().getName(),
                mission.getPoint(),
                mission.getStatus()
        );
    }

    public static MissionResDTO.MissionPage toMissionPage(
            List<Mission> missions, int page, int size, boolean hasNext
    ) {
        return new MissionResDTO.MissionPage(
                missions.stream()
                        .map(MissionConverter::toMissionList)
                        .collect(Collectors.toList()),
                page,
                size,
                hasNext
        );
    }

    public static MissionResDTO.CompleteMissionRes toCompleteMission(Mission mission) {
        return new MissionResDTO.CompleteMissionRes(
                mission.getId(),
                "미션이 정상적으로 완료되었습니다."
        );
    }
}
