package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/userInfo")
    public ApiResponse<HomeResDto.UserInfoResDto> getUserInfo() {
        HomeResDto.UserInfoResDto result = HomeResDto.UserInfoResDto.builder()
                .name("testUser")
                .point(1500)
                .build();
        return ApiResponse.onSuccess(result);
    }

    @GetMapping
    public ApiResponse<HomeResDto.RegionMissionResDto> getRegionMissions (
            @RequestParam String region) {
        HomeResDto.RegionMissionResDto result = HomeResDto.RegionMissionResDto.builder()
                .region(region)
                .missions(List.of())
                .build();
        return ApiResponse.onSuccess(result);
    }
}
