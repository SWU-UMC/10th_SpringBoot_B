package com.example.umc10th_wony.domain.review.entity;

import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰 내용
    @Column(nullable = false)
    private String content;

    // 평점
    @Column(nullable = false)
    private Integer score;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 어떤 미션에 대한 리뷰인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;
}
