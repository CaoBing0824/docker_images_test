package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.MuiltDataNotEmptyValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 多值空判断注解
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MuiltDataNotEmptyValidtor.class})
@Documented
public @interface MuiltDataNotEmptyValidAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};
}
