package com.umc.umc10th.kaka.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "멤버를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER409", "이미 존재하는 멤버입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}