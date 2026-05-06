package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.Preference;
import com.example.umc10th.domain.member.entity.mapping.UserTermAgreement;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.UserType;
import com.example.umc10th.domain.mission.entity.mapping.Participate;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_number_status")
    private Boolean phoneNumberStatus;

    @Column(name = "user_point")
    private Integer userPoint;

    @OneToMany(mappedBy = "member")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Participate> participateList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Preference> preferenceList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<UserTermAgreement> userTermAgreementList = new ArrayList<>();

}
