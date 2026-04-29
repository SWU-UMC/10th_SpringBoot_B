package com.umc.umc10th.kaka.domain.home.exception.code;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HomeSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "HOME200_1", "성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
