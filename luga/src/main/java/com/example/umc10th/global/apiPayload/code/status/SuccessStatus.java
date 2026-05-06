package com.example.umc10th.global.apiPayload.code.status;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "요청 성공"),
    CREATED(HttpStatus.CREATED, "COMMON201", "생성 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
