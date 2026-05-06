package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Mission", description = "'미션 관련 API")
@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    @Operation(
            summary = "미션 목록 조회",
            description = "상태별 미션 목록 조회",
            parameters = @Parameter(name = "status", description = "미션 상태", example = "IN_PROGRESS")
    )
    @GetMapping
    public ApiResponse<MissionResDto.MissionListResDto> getMissions(
            @RequestParam(defaultValue = "IN_PROGRESS") String status) {
        MissionResDto.MissionListResDto result = MissionResDto.MissionListResDto.builder()
                .missions(List.of())
                .hasNext(false)
                .build();
        return ApiResponse.onSuccess(result);
    }

    @Operation(
            summary = "미션 성공 처리",
            description = "미션 ID를 경로 변수로 받아 해당 미션 성공 처리 진행",
            parameters = @Parameter(name = "missionId", description = "성공 처리할 미션 ID", example = "1")
    )
    @PostMapping("/{missionId}/success")
    public ApiResponse<MissionResDto.MissionSuccessResDto> completeMission(
            @PathVariable Long missionId) {
        MissionResDto.MissionSuccessResDto result = MissionResDto.MissionSuccessResDto.builder()
                .missionId(missionId)
                .completedAt(LocalDateTime.now())
                .build();
        return ApiResponse.onSuccess(result);
    }
}
