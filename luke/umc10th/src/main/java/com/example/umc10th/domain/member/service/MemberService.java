package com.example.umc10th.domain.member.service;

import ch.qos.logback.core.status.ErrorStatus;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.repository.MarketRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.ParticipateRepository;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;
    private final MissionRepository missionRepository;
    private final ParticipateRepository participateRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResDTO.SignupDTO signup(
            MemberReqDTO.SignupDTO request
    ) {

        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(
                    MemberErrorCode.MEMBER_ALREADY_EXISTS
            );
        }

        Member member = MemberConverter.toMember(
                request,
                passwordEncoder.encode(request.getPassword())
        );

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignupDTO(savedMember);
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageDTO(member);

    }
}
