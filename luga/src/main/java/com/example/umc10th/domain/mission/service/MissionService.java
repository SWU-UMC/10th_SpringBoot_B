package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
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
    public MissionResDto.MissionListResDto getMissions(String status, int page, int size) {
        // 임시로 userId=1L 사용
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        MissionStatus missionStatus = MissionStatus.valueOf(status);
        Slice<UserMission> slice = userMissionRepository.findByUserAndStatus(
                user, missionStatus, PageRequest.of(page, size));

        return MissionConverter.toMissionListResDto(slice);
    }

    @Transactional
    public MissionResDto.MissionSuccessResDto completeMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));
        userMission.complete();

        return MissionConverter.toMissionSuccessResDto(userMission);
    }
}