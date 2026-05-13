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
                userMission.getStatus()
        );
    }

    // 공통 페이징 Dto 적용
    public static CursorPageResDto<MissionResDto.MissionDto> toMissionListResDto(Slice<UserMission> slice) {
        List<MissionResDto.MissionDto> missions = slice.getContent().stream()
                .map(MissionConverter::toMissionDto)
                .toList();

        return CursorPageResDto.of(missions, slice.hasNext());
    }

    public static MissionResDto.MissionSuccessResDto toMissionSuccessResDto(UserMission userMission) {
        return new MissionResDto.MissionSuccessResDto(
                userMission.getMission().getId(),
                userMission.getCompletedAt()
        );
    }
}
