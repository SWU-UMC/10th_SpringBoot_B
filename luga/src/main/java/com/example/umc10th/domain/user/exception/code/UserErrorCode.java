package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "사용자를 찾을 수 없음"),
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 존재하는 회원임"),
    FOOD_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "음식 종류를 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
