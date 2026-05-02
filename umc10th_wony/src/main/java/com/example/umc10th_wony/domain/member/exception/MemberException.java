package com.example.umc10th_wony.domain.member.exception;

import com.example.umc10th_wony.global.apiPayload.exception.ProjectException;
import com.example.umc10th_wony.domain.member.exception.code.MemberErrorCode;

public class MemberException extends ProjectException {

    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}