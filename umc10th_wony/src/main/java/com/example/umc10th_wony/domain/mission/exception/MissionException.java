package com.example.umc10th_wony.domain.mission.exception;

import com.example.umc10th_wony.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th_wony.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {

    public MissionException(MissionErrorCode errorCode) {
        super(errorCode);
    }
}
