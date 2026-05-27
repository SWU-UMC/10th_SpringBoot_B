package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "리뷰를 찾을 수 없음"),
    INVALID_SORT_TYPE(HttpStatus.BAD_REQUEST, "REVIEW4002", "유효하지 않은 리뷰");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
