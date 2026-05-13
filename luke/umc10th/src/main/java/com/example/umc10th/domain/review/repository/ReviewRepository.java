package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

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

    Slice<Review> findByMemberIdAndStarsLessThanOrderByStarsDescIdDesc(
            Long memberId,
            Integer stars,
            Pageable pageable
    );

}
