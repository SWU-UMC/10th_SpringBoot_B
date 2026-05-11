package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Market;
import com.example.umc10th.domain.mission.repository.MarketRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    public ReviewResDTO.CreateReviewDTO createReview(
            Long memberId,
            Long marketId,
            ReviewReqDTO.CreateReviewDTO request
    ) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        Market market = marketRepository.findById(marketId)
                .orElseThrow(() ->
                        new ReviewException(ReviewErrorCode.MARKET_NOT_FOUND));

        Review review = ReviewConverter.toReview(
                member,
                market,
                request
        );

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewDTO(savedReview);

    }

}
