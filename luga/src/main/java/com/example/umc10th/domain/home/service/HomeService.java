package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.dto.HomeResDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.restaurant.entity.Region;
import com.example.umc10th.domain.restaurant.repository.RegionRepository;
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
public class HomeService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public HomeResDto.UserInfoResDto getUserInfo() {
        // 임시로 userId=1L 사용
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return HomeResDto.UserInfoResDto.builder()
                .name(user.getName())
                .point(Integer.parseInt(user.getUserPoint() == null ? "0" : user.getUserPoint()))
                .build();
    }

    @Transactional(readOnly = true)
    public HomeResDto.RegionMissionResDto getRegionMissions(String regionName, int page, int size) {
        Region region = regionRepository.findByName(regionName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));

        Slice<Mission> slice = missionRepository.findMissionsByRegion(
                region, PageRequest.of(page, size));

        List<MissionResDto.MissionDto> missions = slice.getContent().stream()
                .map(m -> MissionResDto.MissionDto.builder()
                        .missionId(m.getId())
                        .title(m.getBody())
                        .point(m.getAward())
                        .status(null)
                        .build())
                .toList();

        return HomeResDto.RegionMissionResDto.builder()
                .region(regionName)
                .missions(missions)
                .build();
    }
}