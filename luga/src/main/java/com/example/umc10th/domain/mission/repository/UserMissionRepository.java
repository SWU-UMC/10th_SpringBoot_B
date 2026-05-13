package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 상태별 미션 조회 (Slice - CursorPage용)
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission " +
            "WHERE um.user = :user AND um.status = :status")
    Slice<UserMission> findByUserAndStatus(@Param("user") User user,
                                           @Param("status") MissionStatus status,
                                           Pageable pageable);

    // 진행 중인 미션 조회 (Page - 오프셋 페이지네이션용)
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission " +
            "WHERE um.user = :user AND um.status = :status")
    Page<UserMission> findPageByUserAndStatus(@Param("user") User user,
                                              @Param("status") MissionStatus status,
                                              Pageable pageable);
}