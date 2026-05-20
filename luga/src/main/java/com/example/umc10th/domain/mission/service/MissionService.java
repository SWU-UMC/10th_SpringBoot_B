package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import com.example.umc10th.global.dto.CursorPageResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CursorPageResDto<MissionResDto.MissionDto> getMissions(
            Long userId, String status, int page, int size) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.MEMBER_NOT_FOUND));


        MissionStatus missionStatus;

        try{
            missionStatus = MissionStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new GeneralException(MissionErrorCode.INVALID_MISSION_STATUS);
        }

        Slice<UserMission> slice = userMissionRepository.findByUserAndStatus(
                user, missionStatus, PageRequest.of(page, size));

        return CursorPageResDto.of(
                slice.getContent().stream()
                        .map(MissionConverter::toMissionDto)
                        .toList(),
                slice.getNumber(),
                slice.hasNext()
        );
    }

    // 진행 중인 미션 조회 - Page
    @Transactional(readOnly = true)
    public Page<MissionResDto.MissionProgressDto> getMyMissions(Long userId, int page, int size) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.MEMBER_NOT_FOUND));

        Page<UserMission> userMissions = userMissionRepository.findPageByUserAndStatus(
                user, MissionStatus.IN_PROGRESS, PageRequest.of(page, size));

        return userMissions.map(MissionConverter::toMissionProgressDto);
    }

    @Transactional
    public MissionResDto.MissionSuccessResDto completeMission(Long userId, Long userMissionId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.MEMBER_NOT_FOUND));

        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        userMission.complete();

        return MissionConverter.toMissionSuccessResDto(userMission);
    }
}