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
import com.umc.umc10th.kaka.domain.store.entity.Store;
import com.umc.umc10th.kaka.domain.store.exception.StoreException;
import com.umc.umc10th.kaka.domain.store.exception.code.StoreErrorCode;
import com.umc.umc10th.kaka.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    private final StoreRepository storeRepository;

    // 가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }


    // 가게 내 미션 목록 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMissionRes> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    idCursor = Long.parseLong(cursorSplit[1]);
                    missionList = missionRepository
                            .findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                                    storeId, idCursor, pageRequest
                            );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {
            missionList = missionRepository
                    .findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        nextCursor = missionList.getContent().getLast().getId() + ":"
                + missionList.getContent().getLast().getId();

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
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