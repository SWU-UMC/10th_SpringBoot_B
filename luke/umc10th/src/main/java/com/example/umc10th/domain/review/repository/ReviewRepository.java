package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findByMemberIdOrderByIdDesc(
            Long memberId,
            Pageable pageable
    );

    Slice<Review> findByMemberIdAndIdLessThanOrderByIdDesc(
            Long memberId,
            Long cursor,
            Pageable pageable
    );

    // STARS 정렬 첫 페이지
    Slice<Review> findByMemberIdOrderByStarsDescIdDesc(
            Long memberId,
            Pageable pageable
    );

    // STARS 복합 cursor 조회
    @Query("""
    SELECT r
    FROM Review r
    WHERE r.member.id = :memberId
    AND (
        r.stars < :stars
        OR (r.stars = :stars AND r.id < :id)
    )
    ORDER BY r.stars DESC, r.id DESC
""")
    Slice<Review> findByStarsCursor(
            @Param("memberId") Long memberId,
            @Param("stars") Integer stars,
            @Param("id") Long id,
            Pageable pageable
    );

}
