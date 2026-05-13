package com.example.umc10th_wony.domain.review.service;

import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.member.exception.MemberException;
import com.example.umc10th_wony.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_wony.domain.member.repository.MemberRepository;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.mission.exception.MissionException;
import com.example.umc10th_wony.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th_wony.domain.mission.repository.MissionRepository;
import com.example.umc10th_wony.domain.review.converter.ReviewConverter;
import com.example.umc10th_wony.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_wony.domain.review.dto.ReviewResDTO;
import com.example.umc10th_wony.domain.review.entity.Review;
import com.example.umc10th_wony.domain.review.repository.ReviewRepository;
import com.example.umc10th_wony.global.pagination.CursorPageResponse;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public ReviewResDTO createReview(ReviewReqDTO request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Review review = ReviewConverter.toEntity(request, member, mission);

        reviewRepository.save(review);

        return ReviewConverter.toResponse(review);
    }

    @Transactional(readOnly = true)
    public CursorPageResponse<ReviewResDTO.MyReviewResponse> getMyReviews(
            Long memberId,
            String sort,
            String cursor,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(0, size);
        List<Review> reviews;

        if ("SCORE".equalsIgnoreCase(sort)) {
            if (cursor == null || cursor.isBlank()) {
                reviews = reviewRepository.findByMember_IdOrderByScoreDescIdDesc(memberId, pageable);
            } else {
                String[] cursorParts = cursor.split(":");
                Integer score = Integer.parseInt(cursorParts[0]);
                Long reviewId = Long.parseLong(cursorParts[1]);

                reviews = reviewRepository.findByScoreCursor(memberId, score, reviewId, pageable);
            }
        } else {
            if (cursor == null || cursor.isBlank()) {
                reviews = reviewRepository.findByMember_IdOrderByIdDesc(memberId, pageable);
            } else {
                Long cursorId = Long.parseLong(cursor);
                reviews = reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(
                        memberId,
                        cursorId,
                        pageable
                );
            }
        }

        List<ReviewResDTO.MyReviewResponse> content = reviews.stream()
                .map(ReviewConverter::toMyReviewResponse)
                .toList();

        String nextCursor = null;

        if (!reviews.isEmpty()) {
            Review last = reviews.get(reviews.size() - 1);

            if ("SCORE".equalsIgnoreCase(sort)) {
                nextCursor = last.getScore() + ":" + last.getId();
            } else {
                nextCursor = String.valueOf(last.getId());
            }
        }

        return CursorPageResponse.<ReviewResDTO.MyReviewResponse>builder()
                .content(content)
                .hasNext(reviews.size() == size)
                .nextCursor(nextCursor)
                .size(content.size())
                .build();
    }
}
