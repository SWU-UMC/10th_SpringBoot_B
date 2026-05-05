package com.umc.umc10th.kaka.domain.home.dto;


public class HomeMyDataResDTO {

    public record MyDataRes(
            String nickname,
            String email,
            String phoneNumber,
            Integer phoneNumberStatus,
            Integer userPoint
    ) {}
}
