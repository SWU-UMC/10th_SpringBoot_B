package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupDTO> signup(
            @RequestBody MemberReqDTO.SignupDTO request
    ) {

        MemberResDTO.SignupDTO response = new MemberResDTO.SignupDTO();
        response.userId = 1L;
        response.message = "회원가입이 완료되었습니다.";

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/home/my")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage() {

        MemberResDTO.MyPageDTO response = new MemberResDTO.MyPageDTO();
        return ApiResponse.onSuccess(response);
    }
}
