package com.umc.umc10th.kaka.domain.home.exception;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import com.umc.umc10th.kaka.global.apiPayLoad.exception.ProjectException;

public class HomeException extends ProjectException {
    public HomeException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}