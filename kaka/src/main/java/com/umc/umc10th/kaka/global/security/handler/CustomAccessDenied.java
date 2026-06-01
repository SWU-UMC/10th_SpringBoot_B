package com.umc.umc10th.kaka.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import com.umc.umc10th.kaka.global.apiPayLoad.code.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import java.io.IOException;


public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        objectMapper.writeValue(response.getWriter(), errorResponse);

    }
}
