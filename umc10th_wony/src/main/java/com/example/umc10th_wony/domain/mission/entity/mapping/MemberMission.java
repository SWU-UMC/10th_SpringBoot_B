package com.example.umc10th_wony.domain.mission.entity.mapping;

import com.example.umc10th_wony.domain.member.entity.Member;
import com.example.umc10th_wony.domain.mission.entity.Mission;
import com.example.umc10th_wony.domain.mission.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status; // IN_PROGRESS, COMPLETED
}
