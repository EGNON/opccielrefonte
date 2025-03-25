package com.ged.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueAccountValidator implements ConstraintValidator<UniqueAccount, Object> {
    @Override
    public void initialize(UniqueAccount uniqueAccount) {
        ConstraintValidator.super.initialize(uniqueAccount);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
