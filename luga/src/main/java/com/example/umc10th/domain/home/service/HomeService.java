package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.converter.HomeConverter;
import com.example.umc10th.domain.home.dto.HomeResDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.restaurant.entity.Region;
import com.example.umc10th.domain.restaurant.repository.RegionRepository;
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
public class HomeService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public HomeResDto.UserInfoResDto getUserInfo() {
        // 임시로 userId=1L 사용
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        return HomeConverter.toUserInfoResDet(user);
    }

    @Transactional(readOnly = true)
    public HomeResDto.RegionMissionResDto getRegionMissions(String regionName, int page, int size) {
        Region region = regionRepository.findByName(regionName)
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        Slice<Mission> slice = missionRepository.findMissionsByRegion(
                region, PageRequest.of(page, size));

        return HomeConverter.toRegionMissionResDto(regionName, slice);
    }
}