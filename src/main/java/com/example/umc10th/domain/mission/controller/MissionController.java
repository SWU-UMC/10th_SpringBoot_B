package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    @GetMapping
    public ApiResponse<MissionResDTO.MissionListDTO> getMissionList(
            @RequestParam(name = "regionName", required = false) String regionName,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ) {

        MissionResDTO.MissionListDTO response = new MissionResDTO.MissionListDTO();
        response.content = List.of();
        response.page = page;
        response.size = size;
        response.hasNext = false;

        return ApiResponse.onSuccess(response);
    }
}