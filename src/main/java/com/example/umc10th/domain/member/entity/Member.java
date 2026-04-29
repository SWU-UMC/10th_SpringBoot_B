package com.example.umc10th.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
