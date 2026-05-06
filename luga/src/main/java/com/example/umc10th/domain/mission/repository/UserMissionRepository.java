package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 미션 화면: 진행중 / 진행완료 미션 목록 (페이징)
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission WHERE um.user = :user AND um.status = :status")
    Slice<UserMission> findByUserAndStatus(@Param("user") User user,
                                           @Param("status") MissionStatus status,
                                           Pageable pageable);
}