package com.umc.umc10th.kaka.domain.member.dto;


public class MemberResDTO {

    public record RequestBody(
            String stringTest,
            Long longTest
    ) {
    }

    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }
}
