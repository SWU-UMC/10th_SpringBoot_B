package com.example.umc10th.domain.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
