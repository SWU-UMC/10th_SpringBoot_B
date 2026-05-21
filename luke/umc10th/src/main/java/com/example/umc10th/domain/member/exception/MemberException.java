package com.example.umc10th.domain.member.exception;

import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final MemberErrorCode errorCode;

    public MemberException(MemberErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
