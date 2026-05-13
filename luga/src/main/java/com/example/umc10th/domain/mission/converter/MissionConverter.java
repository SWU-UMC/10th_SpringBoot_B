package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Slice;

import java.util.List;

public class MissionConverter {

    public static MissionResDto.MissionDto toMissionDto(UserMission userMission) {
        return new MissionResDto.MissionDto(
                userMission.getMission().getId(),
                userMission.getMission().getBody(),
                userMission.getMission().getAward(),
                userMission.getStatus()
        );
    }

    public static MissionResDto.MissionListResDto toMissionListResDto(Slice<UserMission> slice) {
        List<MissionResDto.MissionDto> missions = slice.getContent().stream()
                .map(MissionConverter::toMissionDto)
                .toList();

        return new MissionResDto.MissionListResDto(missions, slice.hasNext());
    }

    public static MissionResDto.MissionSuccessResDto toMissionSuccessResDto(UserMission userMission) {
        return new MissionResDto.MissionSuccessResDto(
                userMission.getMission().getId(),
                userMission.getCompletedAt()
        );
    }
}
