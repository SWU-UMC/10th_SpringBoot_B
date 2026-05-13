package com.umc.umc10th.kaka.domain.mission.converter;

import com.umc.umc10th.kaka.domain.mission.dto.MissionReqDTO;
import com.umc.umc10th.kaka.domain.mission.dto.MissionResDTO;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import com.umc.umc10th.kaka.domain.mission.entity.mapping.MemberMission;
import com.umc.umc10th.kaka.domain.mission.enums.MissionStatus;
import com.umc.umc10th.kaka.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionList toMissionList(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return new MissionResDTO.MissionList(
                mission.getId(),
                mission.getStore().getName(),
                mission.getPoint(),
                memberMission.getStatus()
        );
    }

    public static MissionResDTO.MissionPage toMissionPage(
            List<MemberMission> memberMissions, int page, int size, boolean hasNext
    ) {
        return new MissionResDTO.MissionPage(
                memberMissions.stream()
                        .map(MissionConverter::toMissionList)
                        .collect(Collectors.toList()),
                page,
                size,
                hasNext
        );
    }

    public static MissionResDTO.CompleteMissionRes toCompleteMission(Mission mission, String message) {
        return new MissionResDTO.CompleteMissionRes(
                mission.getId(),
                message
        );
    }

    public static MemberMission toMemberMission(Mission mission) {
        return MemberMission.builder()
                .mission(mission)
                .status(MissionStatus.COMPLETE)
                .build();
    }

    public static MissionResDTO.StoreMissionList toStoreMissionList(Mission mission) {
        return new MissionResDTO.StoreMissionList(
                mission.getId(),
                mission.getPoint(),
                mission.getConditional()
        );
    }

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline().atStartOfDay())
                .build();
    }

    // 가게 내 미션들 조회
    public static MissionResDTO.GetMissionRes toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMissionRes.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize

    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

}
