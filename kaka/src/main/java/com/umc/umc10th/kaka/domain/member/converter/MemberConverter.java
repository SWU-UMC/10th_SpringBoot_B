package com.umc.umc10th.kaka.domain.member.converter;

import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.enums.Gender;
import com.umc.umc10th.kaka.domain.mission.enums.Address;

import java.time.LocalDate;

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

    public static Member toMember(SignUpReqDTO.SignUpReqBody dto) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .phoneNumber(dto.phoneNumber())
                .agreedId(dto.agreedId())
                .gender(Gender.valueOf(dto.gender()))
                .birth(LocalDate.parse(dto.birth()))
                .address(Address.valueOf(dto.address()))
                .build();
    }
}
