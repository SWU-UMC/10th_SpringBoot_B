package com.example.umc10th_wony.domain.mission.repository;

import com.example.umc10th_wony.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        JOIN FETCH s.location l
        WHERE l.id = :locationId
    """)
    Page<Mission> findByLocation(
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}
