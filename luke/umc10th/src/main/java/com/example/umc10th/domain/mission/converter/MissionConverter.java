package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Market;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.mission.enums.MissionStatus;

public class MissionConverter {

    public static Mission toMission(
            Market market,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .market(market)
                .content(dto.content())
                .point(dto.point())
                .endDate(dto.deadline().atStartOfDay())
                .missionStatus(MissionStatus.IN_PROGRESS)
                .build();
    }

    public static MissionResDTO.MissionDTO toMissionDTO(
            Participate participate
    ) {

        Mission mission = participate.getMission();

        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .marketName(mission.getMarket().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .status(participate.getStatus().name())
                .build();
    }

    public static MissionResDTO.MissionDTO toHomeMissionDTO(
            Mission mission
    ) {

        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .marketName(mission.getMarket().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .status(mission.getMissionStatus().name())
                .build();
    }

}
