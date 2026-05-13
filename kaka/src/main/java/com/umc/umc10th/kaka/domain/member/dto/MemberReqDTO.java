package com.umc.umc10th.kaka.domain.member.dto;




public class MemberReqDTO {

    public record RequestBody(String stringTest, String longTest) {}
    public record GetInfo(Long id) {}
}


