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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
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

    public ReviewResDTO.ReviewPage getMyReviews(ReviewReqDTO.GetMyReviewsReq req) {
        PageRequest pageRequest = PageRequest.of(0, req.pageSize());

        Slice<Review> reviews;
        String nextCursor;

        boolean isFirst = req.cursor().equals("-1");

        switch (req.query().toLowerCase()) {
            case "id" -> {
                reviews = isFirst
                        ? reviewRepository.findByMemberIdOrderById(req.memberId(), pageRequest)
                        : reviewRepository.findByMemberIdAndIdLessThan(
                        req.memberId(), Long.parseLong(req.cursor()), pageRequest);
                nextCursor = reviews.hasNext()
                        ? String.valueOf(reviews.getContent().getLast().getId())
                        : null;
            }
            case "stars" -> {
                reviews = isFirst
                        ? reviewRepository.findByMemberIdOrderByStars(req.memberId(), pageRequest)
                        : reviewRepository.findByMemberIdAndStarsLessThan(
                        req.memberId(), Float.parseFloat(req.cursor()), pageRequest);
                nextCursor = reviews.hasNext()
                        ? String.valueOf(reviews.getContent().getLast().getStars())
                        : null;
            }
            default -> throw new RuntimeException("유효하지 않은 정렬 기준입니다.");
        }

        return ReviewConverter.toReviewPage(reviews, nextCursor, req.pageSize());
    }
}
