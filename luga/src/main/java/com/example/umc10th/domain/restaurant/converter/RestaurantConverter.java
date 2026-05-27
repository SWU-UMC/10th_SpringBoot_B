package com.example.umc10th.domain.restaurant.converter;

import com.example.umc10th.domain.restaurant.dto.RestaurantReqDto;
import com.example.umc10th.domain.restaurant.dto.RestaurantResDto;
import com.example.umc10th.domain.restaurant.entity.Region;
import com.example.umc10th.domain.restaurant.entity.Restaurant;

import java.time.LocalDateTime;

public class RestaurantConverter {

    public static Restaurant toRestaurant(RestaurantReqDto.CreateRestaurantReqDto request, Region region) {
        return Restaurant.builder()
                .region(region)
                .name(request.name())
                .address(request.address())
                .build();
    }

    public static RestaurantResDto.CreateRestaurantResDto toCreateRestaurantResDto(Restaurant restaurant) {
        return new RestaurantResDto.CreateRestaurantResDto(
                restaurant.getId(),
                LocalDateTime.now()
        );
    }
}
