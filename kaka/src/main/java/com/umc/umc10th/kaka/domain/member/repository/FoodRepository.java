package com.umc.umc10th.kaka.domain.member.repository;

import com.umc.umc10th.kaka.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
}
