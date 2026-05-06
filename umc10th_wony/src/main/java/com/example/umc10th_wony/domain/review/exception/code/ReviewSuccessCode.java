package com.example.umc10th_wony.domain.review.exception.code;

import com.example.umc10th_wony.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW200_1", "리뷰 생성에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}