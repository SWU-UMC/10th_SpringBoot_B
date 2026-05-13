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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    public Slice<ReviewResDTO.MyReviewPreviewDTO>
    getMyReviews(
            Long memberId,
            Long cursor,
            Integer size,
            String sort
    ){

        Pageable pageable = PageRequest.of(0, size);

        Slice<Review> reviewSlice;

        if(sort.equals("stars")){

            Integer starCursor =
                    cursor == null
                            ? Integer.MAX_VALUE
                            : cursor.intValue();

            reviewSlice =
                    reviewRepository
                            .findByMemberIdAndStarsLessThanOrderByStarsDescIdDesc(
                                    memberId,
                                    starCursor,
                                    pageable
                            );

        }

        else {

            Long idCursor =
                    cursor == null
                            ? Long.MAX_VALUE
                            : cursor;

            reviewSlice =
                    reviewRepository
                            .findByMemberIdAndIdLessThanOrderByIdDesc(
                                    memberId,
                                    idCursor,
                                    pageable
                            );
        }


        return reviewSlice.map(
                ReviewConverter::toMyReviewPreviewDTO
        );
    }

}
