package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.mission.enums.ParticipatedStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long> {

    @Query("""
        SELECT p
        FROM Participate p
        JOIN FETCH p.mission m
        WHERE p.member.id = :memberId
        AND p.status = :status
    """)
    Page<Participate> findMissionListByMemberAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") ParticipatedStatus status,
            Pageable pageable
    );
}
