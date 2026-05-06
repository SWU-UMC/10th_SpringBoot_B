package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Home", description = "홈 화면 관련 API")
@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    @Operation(summary = "유저 정보 조회", description = "홈 화면에서 유저의 이름 & 포인트 조회")
    @GetMapping("/user-info")
    public ApiResponse<HomeResDto.UserInfoResDto> getUserInfo() {
        HomeResDto.UserInfoResDto result = HomeResDto.UserInfoResDto.builder()
                .name("testUser")
                .point(1500)
                .build();
        return ApiResponse.onSuccess(result);
    }

    @Operation(
            summary = "지역별 미션 목록 조회",
            description = "특정 지역의 미션 목록 조회",
            parameters = @Parameter(name = "region", description = "조회할 지역명", example = "노원구", required = true)
    )
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
