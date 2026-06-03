package com.example.umc10th_wony.domain.member.converter;

import com.example.umc10th_wony.domain.member.dto.MemberResDTO;
import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.global.security.dto.OAuthDTO;

public class MemberConverter {

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .nickname(dto.getName())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .isPhoneVerified(false)
                .point(0)
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }
}
