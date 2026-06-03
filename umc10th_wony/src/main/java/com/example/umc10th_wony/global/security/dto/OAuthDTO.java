package com.example.umc10th_wony.global.security.dto;

import com.example.umc10th_wony.domain.member.enums.SocialType;

public interface OAuthDTO {

    SocialType getSocialType();

    String getSocialUid();

    String getSocialEmail();

    String getName();
}