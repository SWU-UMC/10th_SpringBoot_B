package com.example.umc10th.domain.member.exception;

import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final ReviewErrorCode errorCode;

    public MemberException(ReviewErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
