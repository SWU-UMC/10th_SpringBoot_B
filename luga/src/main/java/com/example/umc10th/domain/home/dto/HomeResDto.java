package com.example.umc10th.domain.home.dto;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.global.dto.CursorPageResDto;

import java.util.List;

public class HomeResDto {

    public record UserInfoResDto (
            String name,
            Integer point
    ) {}

    public record RegionMissionResDto(
            String region,
            CursorPageResDto<MissionResDto.MissionDto> missions
    ) {}
}
