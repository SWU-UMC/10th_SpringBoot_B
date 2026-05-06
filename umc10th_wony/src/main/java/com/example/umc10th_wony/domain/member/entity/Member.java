package com.example.umc10th_wony.domain.member.entity;

import com.example.umc10th_wony.domain.member.enums.Gender;
import com.example.umc10th_wony.domain.member.enums.SocialType;
import com.example.umc10th_wony.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_wony.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = true, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SocialType socialType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    // 내가 수행한 미션들
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    // 내가 작성한 리뷰들
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

//    public void addMemberMission(MemberMission memberMission) {
//        this.memberMissionList.add(memberMission);
//    }
//
//    public void addReview(Review review) {
//        this.reviewList.add(review);
//    }
}