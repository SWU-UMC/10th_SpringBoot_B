package com.example.umc10th.domain.user.entity.mapping;

import java.io.Serializable;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FoodPreferenceId implements Serializable {
    private Long user;
    private Long foodType;
}