package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.restaurant.entity.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 페이징 : 지역 별 미션 리스트
    @Query("SELECT m FROM Mission m WHERE m.region = :region")
    Slice<Mission> findMissionsByRegion(@Param("region") Region region, Pageable pageable);
}
