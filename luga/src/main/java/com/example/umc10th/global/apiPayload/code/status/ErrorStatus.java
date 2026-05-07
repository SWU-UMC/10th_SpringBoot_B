package com.example.umc10th.global.apiPayload.code.status;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 공통
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러 발생"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증 필요"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근 권한 없음"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 리소스를 찾을 수 없음"),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "사용자를 찾을 수 없음"),
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 존재하는 회원임"),

    // Mission
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션을 찾을 수 없음"),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4002", "이미 완료된 미션"),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "리뷰를 찾을 수 없음"),

    // Home
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "HOME4001", "해당 지역을 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
