package com.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foodType")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;
}
