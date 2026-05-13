package com.example.umc10th.domain.home.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HomeErrorCode implements BaseErrorCode {

    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "HOME4001", "해당 지역을 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
