package com.umc.umc10th.kaka.global.validation.validator;

import com.umc.umc10th.kaka.global.validation.annotation.ValidStars;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidStarsValidator implements ConstraintValidator<ValidStars, Float> {

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        if (value == null) return false;
        if (value < 0.5f || value > 5.0f) return false;
        // 0.5 단위 체크
        return (value * 10) % 5 == 0;
    }
}