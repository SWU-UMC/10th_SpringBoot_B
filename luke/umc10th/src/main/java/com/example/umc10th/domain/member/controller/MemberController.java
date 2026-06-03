package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.security.AuthMember;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupDTO> signup(
            @RequestBody MemberReqDTO.SignupDTO request
    ) {

        MemberResDTO.SignupDTO response =
                memberService.signup(request);

        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.MemberLoginResponse> login(
            @RequestBody MemberReqDTO.MemberLoginRequest request
    ) {

        return ApiResponse.onSuccess(
                memberService.login(request)
        );
    }

    @GetMapping("/home/my")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {

        MemberResDTO.MyPageDTO response =
                memberService.getMyPage(authMember);

        return ApiResponse.onSuccess(response);
    }
}
