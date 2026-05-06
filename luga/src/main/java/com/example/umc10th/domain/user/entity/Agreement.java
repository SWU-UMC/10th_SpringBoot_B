package com.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agreement")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default private Integer agreeAge14 = 0;
    @Builder.Default private Integer agreeService = 0;
    @Builder.Default private Integer agreePrivate = 0;
    @Builder.Default private Integer agreeLocation = 0;
    @Builder.Default private Integer agreeMarketing = 0;
    @Builder.Default private Integer agreeEventAlarm = 0;
    @Builder.Default private Integer agreeReviewAlarm = 0;
    @Builder.Default private Integer agreeQaAlarm = 0;
}
