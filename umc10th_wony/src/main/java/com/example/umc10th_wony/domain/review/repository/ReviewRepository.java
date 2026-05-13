package com.example.umc10th_wony.domain.review.repository;

import com.example.umc10th_wony.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID순 첫 조회
    List<Review> findByMember_IdOrderByIdDesc(
            Long memberId,
            Pageable pageable
    );

    // ID순 커서 조회
    List<Review> findByMember_IdAndIdLessThanOrderByIdDesc(
            Long memberId,
            Long cursorId,
            Pageable pageable
    );

    // SCORE순 첫 조회
    List<Review> findByMember_IdOrderByScoreDescIdDesc(
            Long memberId,
            Pageable pageable
    );

    // SCORE순 커서 조회
    @Query("""
        SELECT r
        FROM Review r
        WHERE r.member.id = :memberId
        AND (
            r.score < :score
            OR (r.score = :score AND r.id < :reviewId)
        )
        ORDER BY r.score DESC, r.id DESC
    """)
    List<Review> findByScoreCursor(
            @Param("memberId") Long memberId,
            @Param("score") Integer score,
            @Param("reviewId") Long reviewId,
            Pageable pageable
    );
}