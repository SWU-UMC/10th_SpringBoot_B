package com.umc.umc10th.kaka.domain.member.entity;

import com.umc.umc10th.kaka.domain.member.entity.mapping.MemberFood;
import com.umc.umc10th.kaka.domain.member.entity.mapping.MemberTerm;
import com.umc.umc10th.kaka.domain.member.enums.Gender;
import com.umc.umc10th.kaka.domain.member.enums.SocialType;
import com.umc.umc10th.kaka.domain.mission.enums.Address;
import com.umc.umc10th.kaka.global.apiPayLoad.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "point")
    private Integer point;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "agree_id")
    private Integer agreedId;

    @Column(name = "token")
    private String token;

    @Column(name = "phone_number_status")
    private Integer phoneNumberStatus;

    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
