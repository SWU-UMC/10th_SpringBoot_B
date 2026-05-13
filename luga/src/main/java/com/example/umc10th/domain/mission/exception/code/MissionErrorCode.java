package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션을 찾을 수 없음"),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4002", "이미 완료된 미션"),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION4003", "유효하지 않은 미션");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
