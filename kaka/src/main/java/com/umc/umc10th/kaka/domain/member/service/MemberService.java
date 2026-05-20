package com.umc.umc10th.kaka.domain.member.service;

import com.umc.umc10th.kaka.domain.member.converter.MemberConverter;
import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Food;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.entity.Term;
import com.umc.umc10th.kaka.domain.member.enums.FoodName;
import com.umc.umc10th.kaka.domain.member.enums.TermName;
import com.umc.umc10th.kaka.domain.member.exception.MemberException;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberErrorCode;
import com.umc.umc10th.kaka.domain.member.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {



    private final MemberRepository memberRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    @Transactional // 나중에 DB 연결
    public SignUpResDTO.SignUpResBody getSignUp(
            SignUpReqDTO.SignUpReqBody dto
    ) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);
        memberRepository.save(member);

        SignUpReqDTO.AgreeReq agree = dto.agree();
        Map<TermName, Boolean> termMap = Map.of(
                TermName.AGE,       agree.age(),
                TermName.SERVICE,   agree.service(),
                TermName.PRIVACY,   agree.privacy(),
                TermName.LOCATION,  agree.location(),
                TermName.MARKETING, agree.marketing()
        );
        termMap.forEach((termName, isAgreed) -> {
            if (isAgreed) {
                Term term = termRepository.findByName(termName)
                        .orElseThrow(() -> new MemberException(MemberErrorCode.TERM_NOT_FOUND));
                memberTermRepository.save(MemberConverter.toMemberTerm(member, term));
            }
        });

        dto.foodList().forEach(foodStr -> {
            FoodName enumFoodName = FoodName.valueOf(foodStr.toUpperCase());

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
