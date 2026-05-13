package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.converter.ParticipateConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.ParticipateResDTO;
import com.example.umc10th.domain.mission.entity.Market;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.enums.ParticipatedStatus;
import com.example.umc10th.domain.mission.exception.MarketException;
import com.example.umc10th.domain.mission.exception.code.MarketErrorCode;
import com.example.umc10th.domain.mission.repository.MarketRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.ParticipateRepository;
import com.example.umc10th.global.apiPayload.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final ParticipateRepository participateRepository;
    private final MissionRepository missionRepository;
    private final MarketRepository marketRepository;

    @Transactional
    public void createMission(
            Long marketId,
            MissionReqDTO.CreateMission dto
    ){
        Market market = marketRepository.findById(marketId)
                .orElseThrow(() -> new MarketException(MarketErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(market, dto);

        missionRepository.save(mission);

    }

    public PageResponseDTO<MissionResDTO.MissionDTO> getMissionList(
            Long memberId,
            ParticipatedStatus status,
            Integer page,
            Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Slice<Participate> participatePage =
                participateRepository.findMissionListByMemberAndStatus(
                        memberId,
                        status,
                        pageable
                );

        List<MissionResDTO.MissionDTO> missionList =
                participatePage.stream()
                        .map(MissionConverter::toMissionDTO)
                        .toList();

        return PageResponseDTO.<MissionResDTO.MissionDTO>builder()
                .content(missionList)
                .page(page)
                .size(size)
                .hasNext(participatePage.hasNext())
                .build();
    }

    public PageResponseDTO<MissionResDTO.MissionDTO> getHomeMissionList(
            String regionName,
            Integer page,
            Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Slice<Mission> missionPage =
                missionRepository.findMissionByRegion(
                        regionName,
                        MissionStatus.IN_PROGRESS,
                        pageable
                );

        List<MissionResDTO.MissionDTO> missionList =
                missionPage.stream()
                        .map(MissionConverter::toHomeMissionDTO)
                        .toList();

        return PageResponseDTO.<MissionResDTO.MissionDTO>builder()
                .content(missionList)
                .page(page)
                .size(size)
                .hasNext(missionPage.hasNext())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ParticipateResDTO.MyMissionPreviewDTO>
    getMyMissions(
            Long memberId,
            Integer page
    ){

        PageRequest pageRequest =
                PageRequest.of(page, 3);

        Page<Participate> participatePage =
                participateRepository
                        .findAllByMemberIdAndStatus(
                                memberId,
                                ParticipatedStatus.CHALLERGING,
                                pageRequest
                        );

        return participatePage.map(
                ParticipateConverter::toMyMissionPreviewDTO
        );
    }

}
