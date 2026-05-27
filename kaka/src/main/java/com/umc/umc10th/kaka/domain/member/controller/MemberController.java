package com.umc.umc10th.kaka.domain.member.controller;

import com.umc.umc10th.kaka.domain.member.dto.*;
import com.umc.umc10th.kaka.domain.member.exception.code.MemberSuccessCode;
import com.umc.umc10th.kaka.domain.member.service.MemberService;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    /* @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/query-paramter")
    public String exception(
            @RequestParam String queryParameter
    ) {
        return memberService.singleParameter(queryParameter);
    }

    @PostMapping("/request-body")
    public MemberResDTO.RequestBody requestBody(
            @RequestBody MemberResDTO.RequestBody dto
    ) {
        return memberService.requestBody(dto);
    }

    @PostMapping("/{pathVariable}")
    public String pathVariable(
            @PathVariable String pathVariable
    ) {
        return memberService.singleParameter(pathVariable);
    }

    @PostMapping("/header")
    public String header(
            @RequestHeader("test") String test
    ) {
        return memberService.singleParameter(test);
    } */


    @PostMapping("/v1/signup")
    public ApiResponse<SignUpResDTO.SignUpResBody> getSignUp(
            @RequestBody @Valid SignUpReqDTO.SignUpReqBody dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getSignUp(dto));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResDTO.LoginResBody> login(
            @RequestBody LoginReqDTO.LoginReqBody dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.login(dto));
    }

}
