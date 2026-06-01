package com.umc.umc10th.kaka.global.security.dto;

import com.umc.umc10th.kaka.domain.member.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
