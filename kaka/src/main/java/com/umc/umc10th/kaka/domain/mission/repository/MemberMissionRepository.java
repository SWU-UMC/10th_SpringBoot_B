package com.umc.umc10th.kaka.domain.mission.repository;

import com.umc.umc10th.kaka.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findByMemberId(Long memberId);
}
