package com.umc.umc10th.kaka.domain.mission.repository;

import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //현재 선택 지역 -> 도전 가능한 미션 목록
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE l.id = :locationId " +
            "ORDER BY m.id DESC")
    List<Mission> findByLocationId(
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}
