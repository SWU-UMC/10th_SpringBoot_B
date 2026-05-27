package com.example.umc10th.global.apiPayload.code.status;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH4001", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH4003", "접근 권한이 없습니다. ");
    
    private final HttpStatus status;
    private final String code;
    private final String message;
}
