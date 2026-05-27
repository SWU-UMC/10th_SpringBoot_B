package com.umc.umc10th.kaka.global.security.service;

import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.enums.SocialType;
import com.umc.umc10th.kaka.domain.member.exception.MemberException;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberErrorCode;
import com.umc.umc10th.kaka.domain.member.repository.MemberRepository;
import com.umc.umc10th.kaka.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }


    public UserDetails loadUserByUidAndSocialType (
        SocialType socialType,
        String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType,username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);

    }
}
