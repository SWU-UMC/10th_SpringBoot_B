package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialLogin;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;
    private String address;
    private String addressDetail;
    private String email;
    private String phone;

    @Builder.Default
    private Integer phoneChecked = 0;

    @Enumerated(EnumType.STRING)
    private SocialLogin socialLogin;

    private String userPoint;

    @Builder.Default
    private Integer newAlarm = 0;

    // 약관동의 도메인 생성 후 작업
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "agree_id")
//    private Agreement agreement;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
