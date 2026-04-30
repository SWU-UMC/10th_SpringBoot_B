package com.umc.umc10th.kaka.domain.home.converter;

import com.umc.umc10th.kaka.domain.home.dto.HomeMyDataResDTO;
import com.umc.umc10th.kaka.domain.home.dto.HomeRegionMissionResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    public static HomeRegionMissionResDTO.MissionListClass toRegionMissionList(Mission mission) {
        return HomeRegionMissionResDTO.MissionListClass.builder()
                .missionId(mission.getId())
                .marketName(mission.getStore().getName())
                .point(mission.getPoint())
                .content(mission.getConditional())
                .build();
    }

    public static HomeRegionMissionResDTO.MissionPageClass toRegionMissionPage(
            List<Mission> missions, int page, int size, boolean hasNext
    ) {
        return HomeRegionMissionResDTO.MissionPageClass.builder()
                .content(missions.stream()
                        .map(HomeConverter::toRegionMissionList)
                        .collect(Collectors.toList()))
                .page(page)
                .size(size)
                .hasNext(hasNext)
                .build();
    }

    public static HomeMyDataResDTO.MyDataResClass toMyData(Member member) {
        return HomeMyDataResDTO.MyDataResClass.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneNumberStatus(member.getPhoneNumberStatus())
                .userPoint(member.getPoint())
                .build();
    }
}