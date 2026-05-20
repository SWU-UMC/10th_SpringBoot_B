package com.example.umc10th_wony.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer reward;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String conditional;

    @Column(nullable = false)
    private LocalDate deadline;

    // 어떤 가게 미션인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
