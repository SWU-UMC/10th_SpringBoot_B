package com.example.umc10th.domain.home.dto;

public class HomeReqDto {

    public record UserInfoReqDto(Long userId) {}

    public record RegionMissionReqDto(
            Long userId,
            String region) {}
}
