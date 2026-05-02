package com.example.umc10th_wony.global.apiPayload.exception;

import com.example.umc10th_wony.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class ProjectException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public ProjectException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}