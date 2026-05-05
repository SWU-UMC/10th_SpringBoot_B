package com.umc.umc10th.kaka.domain.home.converter;

import com.umc.umc10th.kaka.domain.home.dto.HomeMyDataResDTO;
import com.umc.umc10th.kaka.domain.home.dto.HomeRegionMissionResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    public static HomeRegionMissionResDTO.MissionList toRegionMissionList(Mission mission) {
        return new HomeRegionMissionResDTO.MissionList(
                mission.getId(),
                mission.getStore().getName(),
                mission.getPoint(),
                mission.getConditional()
        );
    }

    public static HomeRegionMissionResDTO.MissionPage toRegionMissionPage(
            List<Mission> missions, int page, int size, boolean hasNext
    ) {
        return new HomeRegionMissionResDTO.MissionPage(
                missions.stream()
                        .map(HomeConverter::toRegionMissionList)
                        .collect(Collectors.toList()),
                page,
                size,
                hasNext
        );
    }

    public static HomeMyDataResDTO.MyDataRes toMyData(Member member) {
        return new HomeMyDataResDTO.MyDataRes(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPhoneNumberStatus(),
                member.getPoint()
        );
    }
}