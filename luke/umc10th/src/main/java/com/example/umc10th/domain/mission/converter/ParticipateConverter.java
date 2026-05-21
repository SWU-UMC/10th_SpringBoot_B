package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.ParticipateResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.Participate;

public class ParticipateConverter {

    public static ParticipateResDTO.MyMissionPreviewDTO
    toMyMissionPreviewDTO(
            Participate participate
    ){

        Mission mission = participate.getMission();

        return ParticipateResDTO.MyMissionPreviewDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .endDate(mission.getEndDate())
                .build();
    }
}
