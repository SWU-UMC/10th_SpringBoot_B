package com.example.umc10th.domain.restaurant.dto;

import java.util.List;

public class RestaurantResDto {

    public record RestaurantDto(
            Long restaurantId,
            Long regionId,
            String name,
            String address,
            String addressDetail
    ) {}

    public record CreateRestaurantResDto(
            Long restaurantId,
            java.time.LocalDateTime createdAt
    ) {}
}
