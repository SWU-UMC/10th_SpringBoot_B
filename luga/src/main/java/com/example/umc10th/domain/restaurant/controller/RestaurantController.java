package com.example.umc10th.domain.restaurant.controller;

import com.example.umc10th.domain.restaurant.dto.RestaurantReqDto;
import com.example.umc10th.domain.restaurant.dto.RestaurantResDto;
import com.example.umc10th.domain.restaurant.service.RestaurantService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Restaurant", description = "식당 관련 API")
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "식당 생성", description = "지역 ID, 식당명, 주소로 식당 생성")
    @PostMapping
    public ApiResponse<RestaurantResDto.CreateRestaurantResDto> createRestaurant(
            @RequestBody RestaurantReqDto.CreateRestaurantReqDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, restaurantService.createRestaurant(request));
    }

    @Operation(summary = "지역 생성")
    @PostMapping("/region")
    public ApiResponse<Long> createRegion(@RequestParam String name) {
        return ApiResponse.onSuccess(restaurantService.createRegion(name));  // ✅ service 위임
    }
}
