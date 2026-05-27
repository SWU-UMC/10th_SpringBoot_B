package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.mapping.FoodPreference;
import com.example.umc10th.domain.user.entity.mapping.FoodPreferenceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodPreferenceRepository extends JpaRepository<FoodPreference, FoodPreferenceId> {}