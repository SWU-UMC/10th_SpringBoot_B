package com.example.umc10th.global.handler;

import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ApiResponse<?>> handleReviewException(
            ReviewException e
    ) {

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResponse.onFailure(
                        e.getErrorCode(),
                        null
                ));
    }
}
