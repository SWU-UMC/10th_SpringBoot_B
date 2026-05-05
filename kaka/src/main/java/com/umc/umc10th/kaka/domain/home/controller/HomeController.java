package com.umc.umc10th.kaka.domain.home.controller;

import com.umc.umc10th.kaka.domain.home.dto.HomeMyDataResDTO;
import com.umc.umc10th.kaka.domain.home.dto.HomeRegionMissionResDTO;
import com.umc.umc10th.kaka.domain.home.exception.code.HomeSuccessCode;
import com.umc.umc10th.kaka.domain.home.service.HomeService;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/v1/missions")
    public ApiResponse<HomeRegionMissionResDTO.MissionPageClass> getRegionMissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = HomeSuccessCode.OK;
        return ApiResponse.onSuccess(code, homeService.getRegionMissions(page, size));
    }

    @GetMapping("/v1/mydata")
    public ApiResponse<HomeMyDataResDTO.MyDataResClass> getMyData(
            @RequestHeader("Authorization") String token
    ) {
        BaseSuccessCode code = HomeSuccessCode.OK;
        return ApiResponse.onSuccess(code, homeService.getMyData(token));
    }
}
