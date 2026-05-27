package com.umc.umc10th.kaka.domain.member.converter;

import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Food;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.entity.Term;
import com.umc.umc10th.kaka.domain.member.entity.mapping.MemberFood;
import com.umc.umc10th.kaka.domain.member.entity.mapping.MemberTerm;
import com.umc.umc10th.kaka.domain.member.enums.Gender;
import com.umc.umc10th.kaka.domain.mission.enums.Address;

import java.time.LocalDate;
import com.umc.umc10th.kaka.global.security.dto.OAuthDTO;

public class MemberConverter {

    public static MemberResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ) {
        return new MemberResDTO.RequestBody(stringTest, longTest);
    }

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ) {
        return new MemberResDTO.GetInfo(
                member.getName(),
                member.getProfileUrl(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPoint()
        );
    }

    // 회원가입
    public static SignUpResDTO.SignUpResBody toSignUp(Member member) {
        return new SignUpResDTO.SignUpResBody(
                member.getId(),
                member.getToken()
        );
    }

    public static Member toMember(SignUpReqDTO.SignUpReqBody dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
//                .phoneNumber(dto.phoneNumber()) // 워크북 회원가입 api는 없어서 임시 주석 처리함.
                .gender(Gender.valueOf(dto.gender()))
                .birth(LocalDate.parse(dto.birth()))
                .address(Address.valueOf(dto.address()))
                .detailAddress(dto.detailAddress())
                .build();
    }

    public static MemberTerm toMemberTerm(Member member, Term term) {
        return new MemberTerm(null, member, term);
    }

    public static MemberFood toMemberFood(Member member, Food food) {
        return new MemberFood(null, member, food);
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return new MemberResDTO.Login(accessToken);
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .socialUid(dto.getSocialUid())
                .socialType(dto.getSocialType())
                .build();
    }
}
