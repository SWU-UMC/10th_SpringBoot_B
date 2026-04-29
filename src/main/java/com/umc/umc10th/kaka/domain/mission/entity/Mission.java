package com.umc.umc10th.kaka.domain.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "conditional")
    private String conditional;

    @Column(name = "point")
    private Integer point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "status")
    private String status;

    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
