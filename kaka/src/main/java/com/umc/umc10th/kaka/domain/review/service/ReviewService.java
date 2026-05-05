package com.umc.umc10th.kaka.domain.review.service;

import com.umc.umc10th.kaka.domain.mission.entity.Store;
import com.umc.umc10th.kaka.domain.mission.repository.StoreRepository;
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
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateReviewRes createReview(
            Long storeId,
            ReviewReqDTO.CreateReviewReq dto
    ) {
        Store store = storeRepository.findById(storeId).get(); //추후 에러 핸들러 연결 현재는 단순 값 꺼내기
        Review review = ReviewConverter.toReview(store,dto);
        reviewRepository.save(review);
        return ReviewConverter.toCreateReview(review);
    }
}
