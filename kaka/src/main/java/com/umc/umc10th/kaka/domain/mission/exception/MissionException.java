package com.umc.umc10th.kaka.domain.mission.exception;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import com.umc.umc10th.kaka.global.apiPayLoad.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) { // BaseErrorCode 받음!
        super(errorCode);
    }
}