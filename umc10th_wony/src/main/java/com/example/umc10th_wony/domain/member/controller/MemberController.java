package com.example.umc10th_wony.domain.member.controller;

import com.example.umc10th_wony.domain.member.dto.MemberSignupRequest;
import com.example.umc10th_wony.domain.member.dto.MemberInfoResponse;
import com.example.umc10th_wony.domain.member.dto.MemberSignupResponse;

import com.example.umc10th_wony.global.apiPayload.ApiResponse;
import com.example.umc10th_wony.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th_wony.global.security.LoginMember;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member API", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    // 1. 회원가입
    @Operation(summary = "회원가입", description = "이메일 + 비밀번호로 회원을 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<MemberSignupResponse>> signup(
            @Valid @RequestBody MemberSignupRequest request
    ) {
        // TODO: memberService.signup(request)
        MemberSignupResponse result = MemberSignupResponse.builder()
                .memberId(1L)
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();
        return ResponseEntity.status(201)
                .body(ApiResponse.onSuccess(MemberSuccessCode.SIGNUP_SUCCESS, result));
    }

    // 2. 내 정보 조회
    @Operation(summary = "내 정보 조회", description = "JWT 토큰으로 인증된 현재 회원의 정보를 반환합니다.")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberInfoResponse>> getMyInfo(
            @Parameter(hidden = true) @LoginMember Long memberId
    ) {
        // TODO: memberService.getMyInfo(memberId)
        MemberInfoResponse result = MemberInfoResponse.builder()
                .memberId(memberId)
                .email("example@email.com")
                .nickname("닉네임")
                .completedMissionCount(5)
                .writtenReviewCount(3)
                .build();
        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_FOUND, result));
    }
}
