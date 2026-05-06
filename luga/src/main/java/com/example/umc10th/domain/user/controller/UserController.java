package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/signup")
    public ApiResponse<UserResDto.SignupResDto> signup(
            @RequestBody UserReqDto.SignupReqDto request) {
        UserResDto.SignupResDto result = UserResDto.SignupResDto.builder()
                .userId(1L)
                .createdAt(LocalDateTime.now())
                .build();
        return ApiResponse.onSuccess(SuccessStatus.CREATED, result);
    }
}
