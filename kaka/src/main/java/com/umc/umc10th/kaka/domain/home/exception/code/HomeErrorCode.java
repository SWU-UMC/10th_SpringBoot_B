package com.umc.umc10th.kaka.domain.home.exception.code;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HomeErrorCode implements BaseErrorCode {
    HOME_NOT_FOUND(HttpStatus.NOT_FOUND, "HOME404_1", "홈 데이터를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}