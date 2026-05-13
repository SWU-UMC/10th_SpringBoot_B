package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Id 내림차순 커서 페이지네이션
    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant " +
    "WHERE r.user = :user " +
    "AND (:cursorId IS NULL OR r.id < :cursorId) " +
    "ORDER BY r.id DESC")

    Slice<Review> findByUserOrderById(@Param("user") User user,
                                      @Param("cursorId") Long cursorId,
                                      Pageable pageable);

    // 별점 내림차순 커서 페이지네이션
    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant " +
            "WHERE r.user = :user " +
            "AND (:cursor IS NULL OR r.grade < :cursor) " +
            "ORDER BY r.grade DESC, r.id DESC")
    Slice<Review> findByUserOrderByGrade(@Param("user") User user,
                                         @Param("cursor") Integer cursor,
                                         Pageable pageable);
}
