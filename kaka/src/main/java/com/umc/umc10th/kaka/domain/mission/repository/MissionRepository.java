package com.umc.umc10th.kaka.domain.mission.repository;

import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //현재 선택 지역 -> 도전 가능한 미션 목록
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE l.id = :locationId " +
            "ORDER BY m.id DESC")
    Slice<Mission> findByLocationId(
            @Param("locationId") Long locationId,
            Pageable pageable
    );

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    // 커서 있을 때
    Slice<Mission> findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
            Long storeId, Long idCursor, Pageable pageable
    );

    // 커서 없을 때
    Slice<Mission> findMissionsByStore_IdOrderByIdDesc(
            Long storeId, Pageable pageable
    );
}
