package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(
            MemberReqDTO.SignupDTO request,
            String encodedPassword
    ) {

        return Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .email(request.getEmail())
                .password(encodedPassword)
                .phoneNumber(request.getPhoneNumber())
                .userType(request.getType())
                .phoneNumberStatus(false)
                .userPoint(0)
                .build();
    }

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {

        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneNumberStatus(member.getPhoneNumberStatus())
                .userPoint(member.getUserPoint())
                .build();
    }

    public static MemberResDTO.SignupDTO toSignupDTO(
            Member member
    ) {

        return MemberResDTO.SignupDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

}
