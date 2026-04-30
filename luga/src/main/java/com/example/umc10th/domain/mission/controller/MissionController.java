package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    @GetMapping
    public ApiResponse<MissionResDto.MissionListResDto> getMissions(
            @RequestParam(defaultValue = "IN_PROGRESS") String status) {
        MissionResDto.MissionListResDto result = MissionResDto.MissionListResDto.builder()
                .missions(List.of())
                .hasNext(false)
                .build();
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/succes")
    public ApiResponse<MissionResDto.MissionSuccessResDto> completeMission(
            @RequestBody MissionReqDto.MissionSuccessReqDto request) {
        MissionResDto.MissionSuccessResDto result = MissionResDto.MissionSuccessResDto.builder()
                .missionId(request.getMissionId())
                .completedAt(LocalDateTime.now())
                .build();
        return ApiResponse.onSuccess(result);
    }
}
