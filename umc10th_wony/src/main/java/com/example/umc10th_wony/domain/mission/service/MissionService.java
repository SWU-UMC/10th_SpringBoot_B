package com.example.umc10th_wony.domain.mission.service;

import com.example.umc10th_wony.domain.mission.converter.MissionConverter;
import com.example.umc10th_wony.domain.mission.dto.HomeMissionResponse;
import com.example.umc10th_wony.domain.mission.dto.MissionCreateRequest;
import com.example.umc10th_wony.domain.mission.dto.MissionResponse;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.mission.entity.Store;
import com.example.umc10th_wony.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_wony.domain.mission.enums.MissionStatus;
import com.example.umc10th_wony.domain.mission.exception.StoreException;
import com.example.umc10th_wony.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th_wony.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th_wony.domain.mission.repository.MissionRepository;
import com.example.umc10th_wony.domain.mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createMission(Long storeId, MissionCreateRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, request);

        missionRepository.save(mission);
    }

    @Transactional(readOnly = true)
    public Page<MissionResponse> getMyMissions(Long memberId, MissionStatus status, Pageable pageable) {

        Page<MemberMission> missions =
                memberMissionRepository.findByMemberAndStatus(memberId, status, pageable);

        return missions.map(MissionConverter::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<HomeMissionResponse> getHomeMissions(Long locationId, Pageable pageable) {

        Page<Mission> missions =
                missionRepository.findByLocation(locationId, pageable);

        return missions.map(MissionConverter::toHomeResponse);
    }

    // 가게 내 미션 조회
    @Transactional(readOnly = true)
    public List<MissionResponse.GetMission> getMissions(Long storeId) {

        List<Mission> missionList = missionRepository.findAllByStore_Id(storeId);

        return missionList.stream()
                .map(MissionConverter::toGetMission)
                .toList();
    }
}
