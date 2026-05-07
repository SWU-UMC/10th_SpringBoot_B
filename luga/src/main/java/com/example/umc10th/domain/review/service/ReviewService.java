package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
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
                                                        ReviewReqDto.CreateReviewReqDto request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));

        // 임시로 userId=1L 사용 (추후 인증 연동 시 변경)
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Review review = Review.builder()
                .body(request.content())
                .grade(request.rating())
                .restaurant(restaurant)
                .user(user)
                .build();

        Review saved = reviewRepository.save(review);
        return ReviewResDto.CreateReviewResDto.builder()
                .reviewId(saved.getId())
                .createdAt(saved.getDate().atStartOfDay())
                .build();
    }
}
