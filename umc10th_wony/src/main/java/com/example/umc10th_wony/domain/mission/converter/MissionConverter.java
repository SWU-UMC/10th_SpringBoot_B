package com.example.umc10th_wony.domain.mission.converter;

import com.example.umc10th_wony.domain.mission.dto.HomeMissionResponse;
import com.example.umc10th_wony.domain.mission.dto.MissionCreateRequest;
import com.example.umc10th_wony.domain.mission.dto.MissionResponse;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.mission.entity.Store;
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

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionCreateRequest dto
    ) {

        return Mission.builder()
                .store(store)
                .title(dto.getMissionTitle())
                .description(dto.getDescription())
                .conditional(dto.getConditional())
                .reward(dto.getReward())
                .deadline(dto.getDeadline())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResponse.GetMission toGetMission(
            Mission mission
    ) {

        return MissionResponse.GetMission.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .conditional(mission.getConditional())
                .deadline(mission.getDeadline())
                .build();
    }
}