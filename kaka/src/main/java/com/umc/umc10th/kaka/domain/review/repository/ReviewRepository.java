package com.umc.umc10th.kaka.domain.review.repository;

import com.umc.umc10th.kaka.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
