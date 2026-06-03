package com.example.umc10th_wony.domain.mission.repository;

import com.example.umc10th_wony.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_wony.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
        AND mm.status = :status
    """)
    Page<MemberMission> findByMemberAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
