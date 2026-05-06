package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.UserTermAgreement;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "is_required")
    private Boolean isRequired;

    private String version;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "term")
    private List<UserTermAgreement> userTermAgreementList = new ArrayList<>();

}
