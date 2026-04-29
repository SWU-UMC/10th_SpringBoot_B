package com.umc.umc10th.kaka.domain.mission.controller;

import com.umc.umc10th.kaka.domain.mission.service.MissionService;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review" )
public class MissionController {

    private final MissionService missionService;

}
