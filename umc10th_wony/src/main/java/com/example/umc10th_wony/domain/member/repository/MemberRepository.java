package com.example.umc10th_wony.domain.member.repository;

import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
        SELECT COUNT(r)
        FROM Review r
        WHERE r.member.id = :memberId
    """)
    Long countReviews(@Param("memberId") Long memberId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(
            SocialType socialType,
            String socialUid
    );
}
