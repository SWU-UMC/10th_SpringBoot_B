package com.example.umc10th.global.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 커스텀 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    // 존재하지 않는 URL
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFound(NoHandlerFoundException e) {
        BaseErrorCode code = ErrorStatus.NOT_FOUND;
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code, null));
    }

    // 지원하지 않는 HTTP 메서드
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException e) {
        BaseErrorCode code = ErrorStatus.BAD_REQUEST;
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code, null));
    }

    // 쿼리 파라미터 누락
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingParam(
            MissingServletRequestParameterException e) {
        BaseErrorCode code = ErrorStatus.BAD_REQUEST;
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }

    // 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        BaseErrorCode code = ErrorStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }
}
