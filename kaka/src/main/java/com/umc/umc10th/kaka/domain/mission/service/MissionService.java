package com.umc.umc10th.kaka.domain.mission.service;

import com.umc.umc10th.kaka.domain.mission.converter.MissionConverter;
import com.umc.umc10th.kaka.domain.mission.dto.MissionReqDTO;
import com.umc.umc10th.kaka.domain.mission.dto.MissionResDTO;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import com.umc.umc10th.kaka.domain.mission.exception.MissionException;
import com.umc.umc10th.kaka.domain.mission.exception.code.MissionErrorCode;
import com.umc.umc10th.kaka.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    // 미션 목록 조회
    public MissionResDTO.MissionPage getMissions(
            String token, int page, int size
    ) {
        Long memberId = Long.parseLong(token); // 임시(나중에 JWT)
        List<Mission> missions = missionRepository.findByMemberId(memberId);
        boolean hasNext = missions.size() > (page + 1) * size;
        return MissionConverter.toMissionPage(missions, page, size, hasNext);
    }

    // 미션 완료
    @Transactional //나중에 DB 연결
    public MissionResDTO.CompleteMissionRes completeMission(
            MissionReqDTO.CompleteMissionReq dto
    ) {
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));
        return MissionConverter.toCompleteMission(mission);
    }
}