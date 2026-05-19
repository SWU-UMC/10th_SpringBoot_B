package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "멤버가 존재하지 않습니다."),
    MARKET_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4002", "가게가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
