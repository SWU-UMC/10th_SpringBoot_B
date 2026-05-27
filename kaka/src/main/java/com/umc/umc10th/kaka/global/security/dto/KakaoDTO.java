package com.umc.umc10th.kaka.global.security.dto;

import com.umc.umc10th.kaka.domain.member.enums.SocialType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO{

    private final String id;
    private final String email;
    private final String name;

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }

    @Override
    public String getSocialUid() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
