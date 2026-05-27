package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {}