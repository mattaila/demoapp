package com.example.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ByteSizeValidator.class})
public @interface ByteSize {

    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String charset() default "UTF-8";
    
    String message() default "{message.common.validation.byte_size}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @interface List {
        ByteSize[] value();
    }
}
