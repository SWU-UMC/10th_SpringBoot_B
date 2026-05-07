package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MissionResDto.MissionListResDto getMissions(String status, int page, int size) {
        // 임시로 userId=1L 사용
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        MissionStatus missionStatus = MissionStatus.valueOf(status);
        Slice<UserMission> slice = userMissionRepository.findByUserAndStatus(
                user, missionStatus, PageRequest.of(page, size));

        List<MissionResDto.MissionDto> missions = slice.getContent().stream()
                .map(um -> MissionResDto.MissionDto.builder()
                        .missionId(um.getMission().getId())
                        .title(um.getMission().getBody())
                        .point(um.getMission().getAward())
                        .status(um.getStatus())
                        .build())
                .toList();

        return MissionResDto.MissionListResDto.builder()
                .missions(missions)
                .hasNext(slice.hasNext())
                .build();
    }

    @Transactional
    public MissionResDto.MissionSuccessResDto completeMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
        userMission.complete();
        return MissionResDto.MissionSuccessResDto.builder()
                .missionId(userMission.getMission().getId())
                .completedAt(userMission.getCompletedAt())
                .build();
    }
}