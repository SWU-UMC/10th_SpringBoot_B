package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer point;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "mission_status")
    private MissionStatus missionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToMany(mappedBy = "mission")
    private List<Participate> participateList = new ArrayList<>();

}
