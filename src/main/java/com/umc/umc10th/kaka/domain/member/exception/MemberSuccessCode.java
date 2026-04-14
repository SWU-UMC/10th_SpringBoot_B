package com.umc.umc10th.kaka.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode {

    MEMBER_FOUND(HttpStatus.OK, "MEMBER200", "멤버 조회 성공"),
    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER201", "멤버 생성 성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}