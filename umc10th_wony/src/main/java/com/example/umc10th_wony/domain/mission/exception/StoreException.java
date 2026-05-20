package com.example.umc10th_wony.domain.mission.exception;

import com.example.umc10th_wony.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_wony.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
