package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    @Getter
    public static class SignupDTO {

        private Long memberId;
        private LocalDateTime createdAt;
    }

    @Builder
    public static class MyPageDTO {
        public String nickname;
        public String email;
        public String phoneNumber;
        public Boolean phoneNumberStatus;
        public Integer userPoint;
    }

}
