package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;

public class MissionException extends RuntimeException {

    private final BaseErrorCode code;

    public MissionException(BaseErrorCode code) {
        this.code = code;
    }

}
