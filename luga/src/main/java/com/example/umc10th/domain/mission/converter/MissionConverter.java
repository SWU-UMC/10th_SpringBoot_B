package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.global.dto.CursorPageResDto;
import org.springframework.data.domain.Slice;

import java.util.List;

public class MissionConverter {

    public static MissionResDto.MissionDto toMissionDto(UserMission userMission) {
        return new MissionResDto.MissionDto(
                userMission.getMission().getId(),
                userMission.getMission().getBody(),
                userMission.getMission().getAward(),
                userMission.getMission().getDDay()
        );
    }

    // 진행 중인 미션 조회용
    public static MissionResDto.MissionProgressDto toMissionProgressDto(UserMission userMission) {
        return new MissionResDto.MissionProgressDto(
                userMission.getMission().getId(),
                userMission.getMission().getBody(),
                Integer.parseInt(userMission.getMission().getAward()),  // String → Integer
                userMission.getMission().getDDay()
        );
    }

    public static MissionResDto.MissionSuccessResDto toMissionSuccessResDto(UserMission userMission) {
        return new MissionResDto.MissionSuccessResDto(
                userMission.getMission().getId(),
                userMission.getCompletedAt()
        );
    }
}
