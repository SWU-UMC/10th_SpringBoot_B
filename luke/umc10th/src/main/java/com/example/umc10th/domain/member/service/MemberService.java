package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버가 존재하지 않습니다."));

        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneNumberStatus(member.getPhoneNumberStatus())
                .userPoint(member.getUserPoint())
                .build();
    }
}
