package com.umc.umc10th.kaka.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.umc10th.kaka.global.apiPayLoad.ApiResponse;
import com.umc.umc10th.kaka.global.apiPayLoad.code.BaseErrorCode;
import com.umc.umc10th.kaka.global.apiPayLoad.code.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
