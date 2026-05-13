package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
            @RequestBody UserReqDto.SignupReqDto request) {
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
}
