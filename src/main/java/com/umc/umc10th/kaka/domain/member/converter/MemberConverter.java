package com.umc.umc10th.kaka.domain.member.converter;

import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ) {
        return MemberResDTO.RequestBody.builder()
                .stringTest(stringTest)
                .longTest(longTest)
                .build();
    }

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    // 회원가입
    public static SignUpResDTO.SignUpResBodyClass toSignUp(Member member) {
        return SignUpResDTO.SignUpResBodyClass.builder()
                .id(member.getId())
                .refreshToken(member.getToken())
                .build();
    }
}
