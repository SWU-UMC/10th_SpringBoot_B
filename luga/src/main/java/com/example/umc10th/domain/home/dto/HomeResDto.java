package com.example.umc10th.domain.home.dto;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class HomeResDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoResDto {
        private String name;
        private Integer point;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegionMissionResDto {
        private String region;
        private List<MissionResDto.MissionDto> missions;
    }
}
