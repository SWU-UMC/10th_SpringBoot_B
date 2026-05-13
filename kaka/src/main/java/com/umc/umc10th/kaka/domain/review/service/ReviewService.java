package com.umc.umc10th.kaka.domain.review.service;

import com.umc.umc10th.kaka.domain.review.converter.ReviewConverter;
import com.umc.umc10th.kaka.domain.review.dto.ReviewReqDTO;
import com.umc.umc10th.kaka.domain.review.dto.ReviewResDTO;
import com.umc.umc10th.kaka.domain.review.entity.Review;
import com.umc.umc10th.kaka.domain.review.repository.ReviewRepository;
import com.umc.umc10th.kaka.domain.store.entity.Store;
import com.umc.umc10th.kaka.domain.store.exception.StoreException;
import com.umc.umc10th.kaka.domain.store.exception.code.StoreErrorCode;
import com.umc.umc10th.kaka.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateReviewRes createReview(
            Long storeId,
            ReviewReqDTO.CreateReviewReq dto
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(store, dto);
        reviewRepository.save(review);
        return ReviewConverter.toCreateReview(review);
    }
}
