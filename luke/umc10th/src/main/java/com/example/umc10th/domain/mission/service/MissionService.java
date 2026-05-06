package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.enums.ParticipatedStatus;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final ParticipateRepository participateRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.MissionListDTO getMissionList(
            Long memberId,
            ParticipatedStatus status,
            Integer page,
            Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Participate> participatePage =
                participateRepository.findMissionListByMemberAndStatus(
                        memberId,
                        status,
                        pageable
                );

        List<MissionResDTO.MissionDTO> missionList =
                participatePage.stream()
                        .map(participate -> MissionResDTO.MissionDTO.builder()
                                .missionId(participate.getMission().getId())
                                .marketName(participate.getMission().getMarket().getName())
                                .point(participate.getMission().getPoint())
                                .status(participate.getStatus().name())
                                .build())
                        .toList();

        return MissionResDTO.MissionListDTO.builder()
                .content(missionList)
                .page(page)
                .size(size)
                .hasNext(participatePage.hasNext())
                .build();
    }

    public MissionResDTO.MissionListDTO getHomeMissionList(
            String regionName,
            Integer page,
            Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Mission> missionPage =
                missionRepository.findMissionByRegion(
                        regionName,
                        MissionStatus.IN_PROGRESS,
                        pageable
                );

        List<MissionResDTO.MissionDTO> missionList =
                missionPage.stream()
                        .map(mission ->
                                MissionResDTO.MissionDTO.builder()
                                        .missionId(mission.getId())
                                        .marketName(mission.getMarket().getName())
                                        .content(mission.getContent())
                                        .point(mission.getPoint())
                                        .status(mission.getMissionStatus().name())
                                        .build()
                        )
                        .toList();

        return MissionResDTO.MissionListDTO.builder()
                .content(missionList)
                .page(page)
                .size(size)
                .hasNext(missionPage.hasNext())
                .build();
    }

}
