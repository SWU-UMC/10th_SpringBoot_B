package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    public static class SignupDTO {
        public Long userId;
        public String message;
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
