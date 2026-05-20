package com.example.umc10th_wony.domain.mission.exception.code;

import com.example.umc10th_wony.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,       "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    ALREADY_COMPLETED(HttpStatus.CONFLICT,         "MISSION409_1", "이미 완료된 미션입니다."),
    NOT_PARTICIPATING(HttpStatus.FORBIDDEN,        "MISSION403_1", "참여 중인 미션이 아닙니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
