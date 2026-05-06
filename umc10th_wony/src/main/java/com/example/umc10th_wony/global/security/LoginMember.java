package com.example.umc10th_wony.global.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * 컨트롤러 파라미터에서 JWT로부터 추출한 현재 로그인 회원 ID를 주입받기 위한 커스텀 어노테이션
 * 사용 예시: public ResponseEntity<?> myMethod(@LoginMember Long memberId)
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : memberId")
public @interface LoginMember {
}
