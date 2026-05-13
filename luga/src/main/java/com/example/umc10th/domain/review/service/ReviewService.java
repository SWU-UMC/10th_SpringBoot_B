package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResDto.CreateReviewResDto createReview(Long restaurantId,
                                                        Long userId,
                                                        ReviewReqDto.CreateReviewReqDto request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        // 임시로 userId=1L 사용 (추후 인증 연동 시 변경)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .body(request.body())
                .grade(request.grade())
                .restaurant(restaurant)
                .user(user)
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResDto(reviewRepository.save(review));
    }
}
