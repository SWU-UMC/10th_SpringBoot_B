package com.umc.umc10th.kaka.domain.member.exception.code;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode { // implements 필수!

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "약관을 찾을 수 없습니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_3", "음식을 찾을 수 없습니다.");

    private final HttpStatus status;  // status로!
    private final String code;
    private final String message;
}