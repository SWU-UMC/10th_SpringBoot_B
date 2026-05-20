package com.example.umc10th.domain.restaurant.repository;

import com.example.umc10th.domain.restaurant.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);
}