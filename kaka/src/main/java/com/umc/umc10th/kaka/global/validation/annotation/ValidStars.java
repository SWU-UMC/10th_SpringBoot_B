package com.umc.umc10th.kaka.global.validation.annotation;

import com.umc.umc10th.kaka.global.validation.validator.ValidStarsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidStarsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStars {
    String message() default "별점은 0.5 단위로 0.5 ~ 5.0 사이여야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
