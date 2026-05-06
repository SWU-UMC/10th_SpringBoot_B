package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Market;
import com.example.umc10th.domain.mission.repository.MarketRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
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
                .orElseThrow(() -> new RuntimeException("멤버가 존재하지 않습니다."));

        Market market = marketRepository.findById(marketId)
                .orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다."));

        Review review = Review.builder()
                .member(member)
                .market(market)
                .stars(request.getStars())
                .content(request.getContent())
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(savedReview.getId())
                .message("리뷰 작성 완료!")
                .build();

    }

}
