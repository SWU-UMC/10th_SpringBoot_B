package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final T result;

    private ApiResponse(Boolean isSuccess, String code, String message, T result) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    // 성공 with result
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true,
                SuccessStatus.OK.getCode(),
                SuccessStatus.OK.getMessage(),
                result);
    }

    // 성공, successCode 지정
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true,
                code.getCode(),
                code.getMessage(),
                result);
    }

    // 실패
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result ) {
        return new ApiResponse<>(false,
                code.getCode(),
                code.getMessage(),
                result);
    }
}
