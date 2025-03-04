package com.ged.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Constraint()
@Documented
public @interface UniqueAccount {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
