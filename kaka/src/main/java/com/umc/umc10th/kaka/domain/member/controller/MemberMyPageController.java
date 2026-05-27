package com.umc.umc10th.kaka.domain.member.controller;

import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberSuccessCode;
import com.umc.umc10th.kaka.domain.member.service.MemberService;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import com.umc.umc10th.kaka.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberMyPageController {

    private final MemberService memberService;

    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getIngo(
            @AuthenticationPrincipal AuthMember member
            ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }
}