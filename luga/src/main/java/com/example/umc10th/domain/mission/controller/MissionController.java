package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.service.MissionService;
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

    private final MissionService missionService;

    @Operation(
            summary = "미션 목록 조회",
            description = "상태별 미션 목록 조회",
            parameters = {
                    @Parameter(name = "status", description = "미션 상태", example = "IN_PROGRESS"),
                    @Parameter(name = "page", description = "페이지 번호 (0부터)", example = "0"),
                    @Parameter(name = "size", description = "페이지 크기", example = "10")
            }
    )
    @GetMapping
    public ApiResponse<MissionResDto.MissionListResDto> getMissions(
            @RequestParam(defaultValue = "IN_PROGRESS") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(missionService.getMissions(status, page, size));
    }

    @Operation(
            summary = "미션 성공 처리",
            description = "미션 ID를 경로 변수로 받아 해당 미션 성공 처리 진행",
            parameters = @Parameter(name = "missionId", description = "성공 처리할 미션 ID", example = "1")
    )
    @PostMapping("/{missionId}/success")
    public ApiResponse<MissionResDto.MissionSuccessResDto> completeMission(
            @PathVariable Long missionId) {
        return ApiResponse.onSuccess(missionService.completeMission(missionId));
    }
}
