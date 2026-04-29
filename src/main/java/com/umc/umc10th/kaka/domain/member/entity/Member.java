package com.umc.umc10th.kaka.domain.member.entity;

import com.umc.umc10th.kaka.domain.member.enums.Gender;
import com.umc.umc10th.kaka.domain.mission.enums.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member" )
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name" )
    private String name;

    @Column(name = "gender" )
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth" )
    private LocalDate birth;

    @Column(name = "address" )
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "email" )
    private String email;

    @Column(name = "profile_url" )
    private String profileUrl;

    @Column(name = "point" )
    private Integer point;

    @Column(name = "phone_number" )
    private String phoneNumber;

    @Column(name = "password" )
    private String password;

    @Column(name = "agree_id" )
    private Integer agreedId;

    @Column(name = "token" )
    private String token;
}
