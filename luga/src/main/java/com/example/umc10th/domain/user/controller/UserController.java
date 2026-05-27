package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import com.example.umc10th.global.security.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "이름, 이메일, 전화번호, 주소로 회원가입")
    @PostMapping("/signup")
    public ApiResponse<UserResDto.SignupResDto> signup(
            @RequestBody @Valid UserReqDto.SignupReqDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, userService.signup(request));
    }

    @Operation(summary = "선호 음식 등록", description = "유저 ID와 선호 음식 타입 목록으로 선호 음식 등록")
    @PostMapping("/{userId}/food-preferences")
    public ApiResponse<UserResDto.AddFoodPreferenceResDto> addFoodPreference(
            @PathVariable Long userId,
            @RequestBody UserReqDto.AddFoodPreferenceReqDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED,
                userService.addFoodPreference(userId, request));
    }

    @Operation(summary = "로그인", description = "이메일, 비밀번호로 로그인 -> JWT 토큰 반환")
    @PostMapping("/login")
    public ApiResponse<UserResDto.LoginResDto> login(
            @RequestBody @Valid UserReqDto.LoginReqDto request) {
        return ApiResponse.onSuccess(SuccessStatus.OK, userService.login(request));
    }

    @Operation(summary = "마이페이지", description = "JWT 토큰으로 내 정보 조회")
    @GetMapping("/my-page")
    public ApiResponse<UserResDto.MyPageResDto> getMyPage(
            @AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.onSuccess(SuccessStatus.OK, userService.getMyPage(authMember));
    }
}
