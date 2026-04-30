package com.example.umc10th.domain.member.dto;

public class MemberResDTO {

    public static class SignupDTO {
        public Long userId;
        public String message;
    }

    public static class MyPageDTO {
        public String nickname;
        public String email;
        public String phoneNumber;
        public Integer phoneNumberStatus;
        public Integer userPoint;
    }

}
