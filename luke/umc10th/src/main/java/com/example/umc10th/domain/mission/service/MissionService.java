package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.mission.enums.ParticipatedStatus;
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
}
