package com.example.umc10th.domain.restaurant.dto;

public class RestaurantReqDto {

    public record CreateRestaurantReqDto(
            Long regionId,
            String name,
            String address
    ) {}
}
