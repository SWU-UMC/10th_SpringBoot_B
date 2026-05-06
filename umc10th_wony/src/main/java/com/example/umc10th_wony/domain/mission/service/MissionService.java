package com.example.umc10th_wony.domain.mission.service;

import com.example.umc10th_wony.domain.mission.converter.MissionConverter;
import com.example.umc10th_wony.domain.mission.dto.HomeMissionResponse;
import com.example.umc10th_wony.domain.mission.dto.MissionResponse;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_wony.domain.mission.enums.MissionStatus;
import com.example.umc10th_wony.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th_wony.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public Page<MissionResponse> getMyMissions(Long memberId, MissionStatus status, Pageable pageable) {

        Page<MemberMission> missions =
                memberMissionRepository.findByMemberAndStatus(memberId, status, pageable);

        return missions.map(MissionConverter::toResponse);
    }

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public Page<HomeMissionResponse> getHomeMissions(Long locationId, Pageable pageable) {

        Page<Mission> missions =
                missionRepository.findByLocation(locationId, pageable);

        return missions.map(MissionConverter::toHomeResponse);
    }
}
