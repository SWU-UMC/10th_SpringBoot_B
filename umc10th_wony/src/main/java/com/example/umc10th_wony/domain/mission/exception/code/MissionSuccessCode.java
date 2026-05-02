package com.example.umc10th_wony.domain.mission.exception.code;

import com.example.umc10th_wony.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_FOUND(HttpStatus.OK,    "MISSION200_1", "미션 목록 조회에 성공했습니다."),
    MISSION_COMPLETED(HttpStatus.OK,     "MISSION200_2", "미션이 완료 처리되었습니다."),
    REVIEW_CREATED(HttpStatus.CREATED,   "MISSION201_1", "리뷰가 성공적으로 작성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

