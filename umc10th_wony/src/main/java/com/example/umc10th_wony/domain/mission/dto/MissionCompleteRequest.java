package com.example.umc10th_wony.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionCompleteRequest {

    @NotBlank(message = "미션 완료 인증 내용은 필수입니다.")
    private String proof; // 인증 내용 (텍스트 or 이미지 URL 등)
}
