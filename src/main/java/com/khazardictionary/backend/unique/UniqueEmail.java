package com.khazardictionary.backend.unique;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author davut
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmailValidator.class})
public @interface UniqueEmail {
    public String message() default "{khazardictionary.validation.constraints.UniqueEmail.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
