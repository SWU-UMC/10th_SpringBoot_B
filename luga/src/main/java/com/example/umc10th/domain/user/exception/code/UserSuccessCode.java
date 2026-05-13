package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER2001", "회원가입 성공"),
    USER_FOUND(HttpStatus.OK, "MEMBER2002", "사용자 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
