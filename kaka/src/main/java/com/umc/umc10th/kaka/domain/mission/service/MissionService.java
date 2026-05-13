package com.umc.umc10th.kaka.domain.mission.service;

import com.umc.umc10th.kaka.domain.mission.converter.MissionConverter;
import com.umc.umc10th.kaka.domain.mission.dto.MissionReqDTO;
import com.umc.umc10th.kaka.domain.mission.dto.MissionResDTO;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import com.umc.umc10th.kaka.domain.mission.entity.mapping.MemberMission;
import com.umc.umc10th.kaka.domain.mission.enums.MissionStatus;
import com.umc.umc10th.kaka.domain.mission.exception.MissionException;
import com.umc.umc10th.kaka.domain.mission.exception.code.MissionErrorCode;
import com.umc.umc10th.kaka.domain.mission.exception.code.MissionSuccessCode;
import com.umc.umc10th.kaka.domain.mission.repository.MemberMissionRepository;
import com.umc.umc10th.kaka.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 미션 목록 조회
    public MissionResDTO.MissionPage getMissions(String token, int page, int size) {
        Long memberId = Long.parseLong(token);

        // 진행중, 진행완료만 조회
        List<MissionStatus> statuses = List.of(
                MissionStatus.CHALLENGING,
                MissionStatus.COMPLETE
        );

        Pageable pageable = PageRequest.of(page, size);
        Slice<MemberMission> missions = memberMissionRepository
                .findByMemberIdAndStatusIn(memberId, statuses, pageable);

        return MissionConverter.toMissionPage(
                missions.getContent(),
                page,
                size,
                missions.hasNext()
        );
    }

    // 미션 완료
    @Transactional
    public MissionResDTO.CompleteMissionRes completeMission(
            MissionReqDTO.CompleteMissionReq dto
    ) {
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        memberMissionRepository.save(MissionConverter.toMemberMission(mission)); // ✅ 저장 추가

        return MissionConverter.toCompleteMission(
                mission,
                MissionSuccessCode.OK.getMessage()
        );
    }
}