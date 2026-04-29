package com.umc.umc10th.kaka.domain.member.dto;

import lombok.Getter;


public class MemberReqDTO {

    @Getter
    public static class RequestBodyClass {
        private String stringTest;
        private String longTest;
    }

    @Getter
    public static class GetInfoClass {
        private Long id;
    }
}


