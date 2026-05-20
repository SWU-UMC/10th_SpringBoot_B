package com.umc.umc10th.kaka.domain.mission.dto;

import com.umc.umc10th.kaka.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    public record MissionList(
            Long missionId,
            String marketName,
            Integer point,
            MissionStatus status
    ) {
    }

    public record MissionPage(
            List<MissionList> content,
            int page,
            int size,
            boolean hasNext
    ) {
    }

    public record CompleteMissionRes(
            Long missionId,
            String message
    ) {
    }

    @Builder
    public record GetMissionRes(
            Long missionId,
            Integer point,
            String conditional

    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
    }

    // 가게 미션 조회용 추가
    public record StoreMissionList(
            Long missionId,
            Integer point,
            String conditional
    ) {
    }

    // 진행중 미션 응답 DTO 추가
    public record MyMissionList(
            Long missionId,
            String storeName,
            Integer point,
            MissionStatus status
    ) {}

    public record MyMissionPage(
            List<MyMissionList> content,
            int page,
            int size,
            boolean hasNext
    ) {}
}
