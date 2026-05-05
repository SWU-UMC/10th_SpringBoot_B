package com.umc.umc10th.kaka.domain.mission.repository;

import com.umc.umc10th.kaka.domain.mission.entity.mapping.MemberMission;
import com.umc.umc10th.kaka.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status IN :statuses " +
            "ORDER BY mm.id DESC")
    List<MemberMission> findByMemberIdAndStatusIn(
            @Param("memberId") Long memberId,
            @Param("statuses") List<MissionStatus> statuses,
            Pageable pageable
    );
}
