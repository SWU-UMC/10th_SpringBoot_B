package com.umc.umc10th.kaka.domain.member.repository;

import com.umc.umc10th.kaka.domain.member.entity.Food;
import com.umc.umc10th.kaka.domain.member.enums.FoodName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(FoodName name);
}
