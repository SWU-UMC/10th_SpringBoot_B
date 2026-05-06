package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MemberReqDTO {

    public static class SignupDTO {
        @Schema(description = "이름", example = "홍길동")
        @NotBlank
        public String name;

        @Schema(description = "성별", example = "MALE")
        @NotNull
        public Gender gender;

        @Schema(description = "생년월일", example = "2003-01-01")
        @NotNull
        public LocalDate birth;

        @Schema(description = "기본 주소")
        @NotBlank
        public String addressLine1;

        @Schema(description = "상세 주소")
        public String addressLine2;

        @Schema(description = "이메일", example = "test@example.com")
        @Email
        @NotBlank
        public String email;

        @Schema(description = "전화번호", example = "01012345678")
        @NotBlank
        public String phoneNumber;

        @Schema(description = "유저 타입")
        @NotNull
        public UserType type;

    }

}
