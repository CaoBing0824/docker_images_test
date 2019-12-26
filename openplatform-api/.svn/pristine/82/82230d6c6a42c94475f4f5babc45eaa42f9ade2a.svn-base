package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.DateTimeAfterNowValidtor;
import com.xy.boot.open.model.annotation.validator.IntegerValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IntegerValidtor.class})
@Documented
public @interface IntegerAnnotation {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?>[] target() default {};
}
