package com.umc.umc10th.kaka.domain.member.service;

import com.umc.umc10th.kaka.domain.member.converter.MemberConverter;
import com.umc.umc10th.kaka.domain.member.dto.*;
import com.umc.umc10th.kaka.domain.member.entity.Food;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.entity.Term;
import com.umc.umc10th.kaka.domain.member.enums.FoodName;
import com.umc.umc10th.kaka.domain.member.enums.TermName;
import com.umc.umc10th.kaka.domain.member.exception.MemberException;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberErrorCode;
import com.umc.umc10th.kaka.domain.member.repository.*;
import com.umc.umc10th.kaka.global.security.entity.AuthMember;
import com.umc.umc10th.kaka.global.security.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberResDTO.GetInfo getInfo(
            AuthMember member
    ) {
        return MemberConverter.toGetInfo(member.getMember());
    }

    public LoginResDTO.LoginResBody login(LoginReqDTO.LoginReqBody dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(member);
        String token = jwtUtil.createAccessToken(authMember);

        return new LoginResDTO.LoginResBody(token);
    }

    @Transactional
    public SignUpResDTO.SignUpResBody getSignUp(
            SignUpReqDTO.SignUpReqBody dto
    ) {
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);
        memberRepository.save(member);

        SignUpReqDTO.AgreeReq agree = dto.agree();

        Set<TermName> requiredTerms = Set.of(
                TermName.AGE,
                TermName.SERVICE,
                TermName.PRIVACY
        );

        Map<TermName, Boolean> termMap = Map.of(
                TermName.AGE,       agree.age(),
                TermName.SERVICE,   agree.service(),
                TermName.PRIVACY,   agree.privacy(),
                TermName.LOCATION,  agree.location(),
                TermName.MARKETING, agree.marketing()
        );

        boolean allRequiredAgreed = requiredTerms.stream()
                .allMatch(term -> Boolean.TRUE.equals(termMap.get(term)));

        if (!allRequiredAgreed) {
            throw new MemberException(MemberErrorCode.REQUIRED_TERM_NOT_AGREED);
        }

        termMap.forEach((termName, isAgreed) -> {
            if (isAgreed) {
                Term term = termRepository.findByName(termName)
                        .orElseThrow(() -> new MemberException(MemberErrorCode.TERM_NOT_FOUND));
                memberTermRepository.save(MemberConverter.toMemberTerm(member, term));
            }
        });

        dto.foodList().forEach(foodStr -> {

            FoodName enumFoodName;
            try {
                enumFoodName = FoodName.valueOf(foodStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new MemberException(MemberErrorCode.INVALID_FOOD_NAME);
            }

            Food food = foodRepository.findByName(enumFoodName)
                    .orElseThrow(() -> new MemberException(MemberErrorCode.FOOD_NOT_FOUND));

            memberFoodRepository.save(MemberConverter.toMemberFood(member, food));
        });
        return MemberConverter.toSignUp(member);
    }

    public String singleParameter(
            String singleParameter
    ) {
        return singleParameter;
    }

    public MemberResDTO.RequestBody requestBody(
            MemberResDTO.RequestBody dto
    ) {
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }


    @Transactional
    public String createUser(

    ) {
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);
        return "OK";
    }

    @Transactional
    public String deleteUser(

    ) {
        memberRepository.deleteByName("test");
        return "OK";
    }
}
