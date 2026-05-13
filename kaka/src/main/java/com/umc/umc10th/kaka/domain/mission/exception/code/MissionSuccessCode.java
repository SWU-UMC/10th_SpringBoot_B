package com.umc.umc10th.kaka.domain.mission.exception.code;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MISSION200_1", "성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
