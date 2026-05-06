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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
