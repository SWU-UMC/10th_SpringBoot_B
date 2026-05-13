package com.umc.umc10th.kaka.domain.review.repository;

import com.umc.umc10th.kaka.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 커서 기반
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.member m " +
            "LEFT JOIN FETCH r.reply rp " +
            "WHERE r.member.id = :memberId " +
            "AND r.id < :cursor " +
            "ORDER BY r.id DESC")
    Slice<Review> findByMemberIdAndIdLessThan(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.member m " +
            "LEFT JOIN FETCH r.reply rp " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.id DESC")
    Slice<Review> findByMemberIdOrderById(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 별점 순 커서 기반
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.member m " +
            "LEFT JOIN FETCH r.reply rp " +
            "WHERE r.member.id = :memberId " +
            "AND r.stars < :cursor " +
            "ORDER BY r.stars DESC")
    Slice<Review> findByMemberIdAndStarsLessThan(
            @Param("memberId") Long memberId,
            @Param("cursor") Float cursor,
            Pageable pageable
    );

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.member m " +
            "LEFT JOIN FETCH r.reply rp " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.stars DESC")
    Slice<Review> findByMemberIdOrderByStars(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
