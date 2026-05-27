package com.example.umc10th.domain.home.converter;

import com.example.umc10th.domain.home.dto.HomeResDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.dto.CursorPageResDto;
import org.springframework.data.domain.Slice;

import java.util.List;

public class HomeConverter {

    public static HomeResDto.UserInfoResDto toUserInfoResDet(User user) {
        int point = user.getUserPoint() == null ? 0 : Integer.parseInt(user.getUserPoint());

        return new HomeResDto.UserInfoResDto(user.getName(), point);
    }

    public static HomeResDto.RegionMissionResDto toRegionMissionResDto(
            String regionName,
            Slice<Mission> slice
    ) {
        List<MissionResDto.MissionDto> missions = slice.getContent().stream()
                .map(HomeConverter::toMissionDto)
                .toList();

        return new HomeResDto.RegionMissionResDto(regionName, CursorPageResDto.of(missions, slice.getNumber(), slice.hasNext()));
    }

    private static MissionResDto.MissionDto toMissionDto(Mission mission) {
        return new MissionResDto.MissionDto(
                mission.getId(),
                mission.getBody(),
                mission.getAward(),
                null
        );
    }
}
