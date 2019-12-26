package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.NumRangeLengthValidtor;
import com.xy.boot.open.model.annotation.validator.NumRangeValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 号段有效判断注解
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NumRangeLengthValidtor.class})
@Documented
public @interface NumRangeLengthAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    int maxlength() default 0;
}
