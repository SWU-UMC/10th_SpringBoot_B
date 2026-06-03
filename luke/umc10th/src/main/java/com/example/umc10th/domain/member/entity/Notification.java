package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.NotifictationType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String detail;

    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotifictationType notifictationType;

    @Column(name = "is_read")
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
