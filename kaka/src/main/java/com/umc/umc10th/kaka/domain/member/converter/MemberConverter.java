package com.umc.umc10th.kaka.domain.member.converter;

import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;

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
}
