package com.example.umc10th_wony.domain.member.repository;

import com.example.umc10th_wony.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
        SELECT COUNT(r)
        FROM Review r
        WHERE r.member.id = :memberId
    """)
    Long countReviews(@Param("memberId") Long memberId);
}
