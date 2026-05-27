package com.example.umc10th.domain.member.service;

import ch.qos.logback.core.status.ErrorStatus;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.FoodCategory;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.Preference;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodCategoryRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.PreferenceRepository;
import com.example.umc10th.domain.member.security.AuthMember;
import com.example.umc10th.domain.mission.repository.MarketRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.ParticipateRepository;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PreferenceRepository preferenceRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

        String accessToken =
                jwtUtil.createAccessToken(
                        new AuthMember(savedMember)
                );

        request.getFoodCategoryIds().forEach(foodCategoryId -> {

            FoodCategory foodCategory =
                    foodCategoryRepository.findById(foodCategoryId)
                            .orElseThrow();

            Preference preference =
                    MemberConverter.toPreference(
                            savedMember,
                            foodCategory
                    );

            preferenceRepository.save(preference);
        });

        return MemberConverter.toSignupDTO(
                savedMember,
                accessToken
        );
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MemberLoginResponse login(MemberReqDTO.MemberLoginRequest request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("사용자를 찾을 수 없습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(
                request.getPassword(),
                member.getPassword()
        )) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성
        String accessToken =
                jwtUtil.createAccessToken(new AuthMember(member));

        return MemberResDTO.MemberLoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MyPageDTO getMyPage(
            AuthMember authMember
    ) {

        Member member = authMember.getMember();

        return MemberConverter.toMyPageDTO(member);

    }
}
