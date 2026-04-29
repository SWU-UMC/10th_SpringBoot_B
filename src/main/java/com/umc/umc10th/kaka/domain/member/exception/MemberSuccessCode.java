package com.umc.umc10th.kaka.domain.member.exception;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200_1",
            "성종적으로 요청을 처리했습니다." ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}