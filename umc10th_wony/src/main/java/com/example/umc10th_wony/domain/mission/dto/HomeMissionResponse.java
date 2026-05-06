package com.example.umc10th_wony.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HomeMissionResponse {

    private Long missionId;
    private String missionTitle;
    private Integer reward;
    private String storeName;
}
