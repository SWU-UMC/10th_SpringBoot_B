package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long marketId;

    private Integer stars;

    private String content;

    private LocalDateTime createdAt;
}