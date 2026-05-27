package com.umc.umc10th.kaka.domain.store.exception;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import com.umc.umc10th.kaka.global.apiPayLoad.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
