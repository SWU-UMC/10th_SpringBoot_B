package com.umc.umc10th.kaka.domain.member.service;

import com.umc.umc10th.kaka.domain.member.converter.MemberConverter;
import com.umc.umc10th.kaka.domain.member.dto.MemberReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.entity.Member;
import com.umc.umc10th.kaka.domain.member.enums.Gender;
import com.umc.umc10th.kaka.domain.member.exception.MemberException;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberErrorCode;
import com.umc.umc10th.kaka.domain.member.repository.MemberRepository;
import com.umc.umc10th.kaka.domain.mission.enums.Address;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfoClass dto
    ) {
        Long memberId = dto.getId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    @Transactional // 나중에 DB 연결
    public SignUpResDTO.SignUpResBodyClass getSignUp(
            SignUpReqDTO.SignUpReqBodyClass dto
    ) {
        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .agreedId(dto.getAgreedId())
                .gender(Gender.valueOf(dto.getGender()))
                .birth(LocalDate.parse(dto.getBirth()))
                .address(Address.valueOf(dto.getAddress()))
                .build();
        memberRepository.save(member);
        return MemberConverter.toSignUp(member);
    }

    public String singleParameter(
            String singleParameter
    ) {
        return singleParameter;
    }

    public MemberResDTO.RequestBody requestBody(
            MemberResDTO.RequestBody dto
    ) {
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }


    @Transactional
    public String createUser(

    ) {
        Member member = Member.builder()
                .name("test")
                .build();
        memberRepository.save(member);
        return "OK";
    }

    @Transactional
    public String deleteUser(

    ) {
        memberRepository.deleteByName("test");
        return "OK";
    }
}
