package com.example.umc10th_wony.domain.mission.converter;

import com.example.umc10th_wony.domain.mission.dto.HomeMissionResponse;
import com.example.umc10th_wony.domain.mission.dto.MissionResponse;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {

    public static MissionResponse toResponse(MemberMission mm) {
        return MissionResponse.builder()
                .missionId(mm.getMission().getId())
                .missionTitle(mm.getMission().getTitle())
                .reward(mm.getMission().getReward())
                .build();
    }

    public static HomeMissionResponse toHomeResponse(Mission mission) {
        return HomeMissionResponse.builder()
                .missionId(mission.getId())
                .missionTitle(mission.getTitle())
                .reward(mission.getReward())
                .storeName(mission.getStore().getName())
                .build();
    }
}