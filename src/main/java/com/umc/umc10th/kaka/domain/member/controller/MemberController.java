package com.umc.umc10th.kaka.domain.member.controller;

import com.umc.umc10th.kaka.domain.member.dto.MemberReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.MemberResDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpReqDTO;
import com.umc.umc10th.kaka.domain.member.dto.SignUpResDTO;
import com.umc.umc10th.kaka.domain.member.exception.MemberSuccessCode;
import com.umc.umc10th.kaka.domain.member.service.MemberService;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth" )
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test" )
    public String test() {
        return "test";
    }

    @PostMapping("/query-paramter" )
    public String exception(
            @RequestParam String queryParameter
    ) {
        return memberService.singleParameter(queryParameter);
    }

    @PostMapping("/request-body" )
    public MemberResDTO.RequestBody requestBody(
            @RequestBody MemberResDTO.RequestBody dto
    ) {
        return memberService.requestBody(dto);
    }

    @PostMapping("/{pathVariable}" )
    public String pathVariable(
            @PathVariable String pathVariable
    ) {
        return memberService.singleParameter(pathVariable);
    }

    @PostMapping("/header" )
    public String header(
            @RequestHeader("test" ) String test
    ) {
        return memberService.singleParameter(test);
    }

    //마이페이지
    @PostMapping("/v1/users/me" )
    public ApiResponse<MemberResDTO.GetInfo> getIngo(
            @RequestBody MemberReqDTO.GetInfoClass dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    @PostMapping("/v1/signup" )
    public ApiResponse<SignUpResDTO.SignUpResBodyClass> getSignUp(
            @RequestBody SignUpReqDTO.SignUpReqBodyClass dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getSignUp(dto));
    }

}
