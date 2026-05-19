package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {

        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneNumberStatus(member.getPhoneNumberStatus())
                .userPoint(member.getUserPoint())
                .build();
    }

}
