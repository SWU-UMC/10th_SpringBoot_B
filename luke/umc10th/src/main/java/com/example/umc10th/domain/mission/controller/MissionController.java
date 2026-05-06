package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.ParticipatedStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.MissionListDTO> getMissionList(
            @RequestParam(name = "memberId", required = false) Long memberId,
            @RequestParam(name = "status") ParticipatedStatus status,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ) {

        return ApiResponse.onSuccess(
                missionService.getMissionList(
                        memberId,
                        status,
                        page,
                        size
                )
        );
    }

    @PostMapping("/completed")
    public ApiResponse<MissionResDTO.CompleteDTO> completeMission(
            @RequestBody MissionReqDTO.CompleteDTO request
    ) {

        MissionResDTO.CompleteDTO response = new MissionResDTO.CompleteDTO();
        response.missionId = request.missionId;
        response.message = "미션이 정상적으로 완료되었습니다.";

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/home")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeMissionList(
            @RequestParam String regionName,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {

        return ApiResponse.onSuccess(
                missionService.getHomeMissionList(
                        regionName,
                        page,
                        size
                )
        );
    }
}