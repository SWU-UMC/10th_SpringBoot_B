package com.umc.umc10th.kaka.domain.member.exception.code;

import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "약관을 찾을 수 없습니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_3", "음식을 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "이미 사용 중인 이메일입니다."),
    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST, "MEMBER400_1", "필수 약관에 동의하지 않았습니다."),
    INVALID_FOOD_NAME(HttpStatus.BAD_REQUEST, "MEMBER400_2", "유효하지 않은 음식 값입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "비밀번호가 일치하지 않습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "MEMBER400_3", "지원하지 않는 소셜 로그인입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}