package com.xy.boot.open.model.annotation;

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
@Constraint(validatedBy = {NumRangeValidtor.class})
@Documented
public @interface NumRangeAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

}
