package com.example.umc10th_wony.domain.member.service;

import com.example.umc10th_wony.domain.member.dto.MyPageResponse;
import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.member.exception.MemberException;
import com.example.umc10th_wony.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_wony.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Long reviewCount = memberRepository.countReviews(memberId);

        return MyPageResponse.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .isPhoneVerified(member.getIsPhoneVerified())
                .point(member.getPoint())
                .reviewCount(reviewCount)
                .build();
    }
}
