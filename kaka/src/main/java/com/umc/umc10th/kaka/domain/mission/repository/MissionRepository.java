package com.umc.umc10th.kaka.domain.mission.repository;

import com.umc.umc10th.kaka.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}
