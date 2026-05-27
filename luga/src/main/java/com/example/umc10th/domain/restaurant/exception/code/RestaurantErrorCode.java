package com.example.umc10th.domain.restaurant.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RestaurantErrorCode implements BaseErrorCode {

    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT4001", "식당을 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
