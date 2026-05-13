package com.umc.umc10th.kaka.domain.home.service;

import com.umc.umc10th.kaka.domain.home.converter.HomeConverter;
import com.umc.umc10th.kaka.domain.home.dto.HomeMyDataResDTO;
import com.umc.umc10th.kaka.domain.home.dto.HomeRegionMissionResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.exception.MemberException;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberErrorCode;
import com.umc.umc10th.kaka.domain.member.repository.MemberRepository;
import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import com.umc.umc10th.kaka.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    // 해당 지역 미션 목록 조회
    public HomeRegionMissionResDTO.MissionPage getRegionMissions(
            Long locationId, int page, int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<Mission> missionSlice = missionRepository.findByLocationId(locationId, pageable);

        return HomeConverter.toRegionMissionPage(
                missionSlice.getContent(),
                page,
                size,
                missionSlice.hasNext()
        );
    }

    // 마이데이터 조회
    public HomeMyDataResDTO.MyDataRes getMyData(String token) {
        Long memberId = Long.parseLong(token); // 임시 (나중에 JWT)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return HomeConverter.toMyData(member);
    }
}