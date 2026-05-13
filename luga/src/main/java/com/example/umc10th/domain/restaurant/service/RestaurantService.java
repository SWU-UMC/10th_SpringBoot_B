package com.example.umc10th.domain.restaurant.service;

import com.example.umc10th.domain.restaurant.converter.RestaurantConverter;
import com.example.umc10th.domain.restaurant.dto.RestaurantReqDto;
import com.example.umc10th.domain.restaurant.dto.RestaurantResDto;
import com.example.umc10th.domain.restaurant.entity.Region;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RegionRepository;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public RestaurantResDto.CreateRestaurantResDto createRestaurant(
            RestaurantReqDto.CreateRestaurantReqDto request) {

        Region region = regionRepository.findById(request.regionId())
                .orElseThrow(() -> new GeneralException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        Restaurant restaurant = RestaurantConverter.toRestaurant(request, region);
        Restaurant saved = restaurantRepository.save(restaurant);

        return RestaurantConverter.toCreateRestaurantResDto(saved);
    }

    // 지역 생성
    @Transactional
    public Long createRegion(String name) {
        Region region = Region.builder().name(name).build();
        return regionRepository.save(region).getId();
    }
}
