package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.Role;
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
    private String password; // BCrypt 암호화된 비밀번호

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    @Builder.Default
    private Integer phoneChecked = 0;

    @Enumerated(EnumType.STRING)
    private SocialLogin socialLogin;

    private String userPoint;

    @Builder.Default
    private Integer newAlarm = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agree_id")
    private Agreement agreement;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
