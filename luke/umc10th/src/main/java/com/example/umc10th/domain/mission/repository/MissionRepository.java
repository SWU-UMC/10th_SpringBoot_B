package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.market mk
        JOIN FETCH mk.region r
        WHERE r.name = :regionName
        AND m.missionStatus = :status
    """)
    Page<Mission> findMissionByRegion(
            @Param("regionName") String regionName,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

}
