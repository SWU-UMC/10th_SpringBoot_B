package com.example.umc10th_wony.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponse {

    private Long memberId;
    private String nickname;
    private String email;

    private String phoneNumber;
    private Boolean isPhoneVerified;

    private Integer point;

    private Long reviewCount;
}