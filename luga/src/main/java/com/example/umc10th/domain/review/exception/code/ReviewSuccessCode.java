package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    UPLOAD_SUCCESS(HttpStatus.CREATED, "REVIEW2001", "리뷰 등록 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
