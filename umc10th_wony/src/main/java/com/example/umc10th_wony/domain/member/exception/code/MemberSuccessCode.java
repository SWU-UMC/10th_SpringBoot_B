package com.example.umc10th_wony.domain.member.exception.code;

import com.example.umc10th_wony.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입에 성공했습니다."),
    MEMBER_FOUND(HttpStatus.OK,        "MEMBER200_1", "성공적으로 유저를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
