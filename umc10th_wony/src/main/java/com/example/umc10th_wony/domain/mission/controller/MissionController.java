package com.example.umc10th_wony.domain.mission.controller;

import com.example.umc10th_wony.domain.mission.dto.MissionCompleteRequest;
import com.example.umc10th_wony.domain.mission.dto.ReviewCreateRequest;
import com.example.umc10th_wony.domain.mission.dto.MissionResponse;
import com.example.umc10th_wony.domain.mission.dto.MissionCompleteResponse;
import com.example.umc10th_wony.domain.mission.dto.ReviewResponse;

import com.example.umc10th_wony.global.apiPayload.ApiResponse;
import com.example.umc10th_wony.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th_wony.global.security.LoginMember;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mission API", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    // 1. 지역별 미션 목록 조회
    @Operation(summary = "지역별 미션 목록 조회", description = "특정 지역의 미션 목록을 조회합니다.")
    @GetMapping("/missions")
    public ResponseEntity<ApiResponse<List<MissionResponse>>> getMissionsByRegion(
            @Parameter(description = "지역명 (예: 서울, 부산)", required = true) @RequestParam String region
    ) {
        // TODO: missionService.getMissionsByRegion(region)
        List<MissionResponse> result = List.of(MissionResponse.builder()
                .missionId(1L).title("서울 미션 예시").description("미션 설명")
                .region(region).reward(1000).status("OPEN").build());
        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_FOUND, result));
    }

    // 2. 내 미션 목록 조회
    @Operation(summary = "내 미션 목록 조회", description = "현재 로그인한 회원이 참여 중인 미션 목록을 반환합니다.")
    @GetMapping("/missions/me")
    public ResponseEntity<ApiResponse<List<MissionResponse>>> getMyMissions(
            @Parameter(hidden = true) @LoginMember Long memberId
    ) {
        // TODO: missionService.getMyMissions(memberId)
        List<MissionResponse> result = List.of(MissionResponse.builder()
                .missionId(2L).title("내 미션 예시").description("미션 설명")
                .region("서울").reward(500).status("IN_PROGRESS").build());
        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_FOUND, result));
    }

    // 3. 미션 완료 처리
    @Operation(summary = "미션 완료 처리", description = "특정 미션을 완료 처리합니다. JWT에서 회원 ID를 추출합니다.")
    @PatchMapping("/missions/{missionId}/complete")
    public ResponseEntity<ApiResponse<MissionCompleteResponse>> completeMission(
            @Parameter(description = "완료할 미션 ID") @PathVariable Long missionId,
            @Valid @RequestBody MissionCompleteRequest request,
            @Parameter(hidden = true) @LoginMember Long memberId
    ) {
        // TODO: missionService.completeMission(missionId, memberId, request)
        MissionCompleteResponse result = MissionCompleteResponse.builder()
                .missionId(missionId).memberId(memberId).status("COMPLETED").rewardPoint(1000).build();
        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, result));
    }

    // 4. 리뷰 작성
    @Operation(summary = "리뷰 작성", description = "완료된 미션에 대해 별점과 텍스트로 리뷰를 작성합니다.")
    @PostMapping("/reviews")
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
            @Valid @RequestBody ReviewCreateRequest request,
            @Parameter(hidden = true) @LoginMember Long memberId
    ) {
        // TODO: reviewService.createReview(request, memberId)
        ReviewResponse result = ReviewResponse.builder()
                .reviewId(1L).missionId(request.getMissionId()).memberId(memberId)
                .rating(request.getRating()).content(request.getContent()).build();
        return ResponseEntity.status(201)
                .body(ApiResponse.onSuccess(MissionSuccessCode.REVIEW_CREATED, result));
    }
}