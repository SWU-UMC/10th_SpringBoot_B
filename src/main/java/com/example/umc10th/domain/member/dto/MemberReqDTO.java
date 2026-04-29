package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.UserType;

import java.time.LocalDate;

public class MemberReqDTO {

    public static class SignupDTO {
        public String name;
        public Gender gender;
        public LocalDate birth;
        public String addressLine1;
        public String addressLine2;
        public String email;
        public String phoneNumber;
        public UserType type;
        public Long agreeId;
    }

}
