package com.example.umc10th.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

    MEMBER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MEMBER4001",
            "멤버가 존재하지 않습니다."
    ),

    MEMBER_ALREADY_EXISTS(
            HttpStatus.BAD_REQUEST,
            "MEMBER4001",
            "이미 존재하는 이메일입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;

}
