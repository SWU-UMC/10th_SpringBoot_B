package com.example.umc10th_wony.global.security.service;

import com.example.umc10th_wony.domain.member.converter.MemberConverter;
import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.member.enums.SocialType;
import com.example.umc10th_wony.domain.member.exception.MemberException;
import com.example.umc10th_wony.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_wony.domain.member.repository.MemberRepository;
import com.example.umc10th_wony.global.security.dto.KakaoDTO;
import com.example.umc10th_wony.global.security.dto.OAuthDTO;
import com.example.umc10th_wony.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuthUser = super.loadUser(userRequest);

        SocialType providerId;

        try {
            providerId = SocialType.valueOf(
                    userRequest.getClientRegistration()
                            .getRegistrationId()
                            .toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        String socialUid = String.valueOf(oAuthUser.getAttribute("id"));

        Map<String, Object> kakaoAccount = oAuthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        OAuthDTO dto;

        switch (providerId) {
            case KAKAO -> {
                String email = String.valueOf(kakaoAccount.get("email"));
                String name = String.valueOf(profile.get("nickname"));

                dto = new KakaoDTO(socialUid, email, name);
            }

            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        Member member = memberRepository.findBySocialTypeAndSocialUid(
                        dto.getSocialType(),
                        dto.getSocialUid()
                )
                .orElseGet(() -> memberRepository.save(MemberConverter.toMember(dto)));

        return new OAuthMember(member, oAuthUser.getAttributes());
    }
}