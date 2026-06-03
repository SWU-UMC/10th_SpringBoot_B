package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository
        extends JpaRepository<FoodCategory, Long> {
}
