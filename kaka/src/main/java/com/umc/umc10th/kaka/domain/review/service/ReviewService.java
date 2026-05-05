package com.umc.umc10th.kaka.domain.review.service;

import com.umc.umc10th.kaka.domain.review.converter.ReviewConverter;
import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.entity.Review;
import com.umc.umc10th.kaka.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResDTO.CreateReviewRes createReview(
            ReviewReqDTO.CreateReviewReq dto
    ) {
        Review review = ReviewConverter.toReview(dto);
        reviewRepository.save(review);
        return ReviewConverter.toCreateReview(review);
    }
}
