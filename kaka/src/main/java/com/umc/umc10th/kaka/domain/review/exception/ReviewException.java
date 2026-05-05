package com.umc.umc10th.kaka.domain.review.exception;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import com.umc.umc10th.kaka.global.apiPayLoad.exception.ProjectException;
import lombok.Getter;

@Getter
public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}