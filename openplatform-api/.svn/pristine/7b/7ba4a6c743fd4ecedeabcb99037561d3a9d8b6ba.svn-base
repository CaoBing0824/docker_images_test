package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.PatternValidtor;
import com.xy.boot.open.model.annotation.validator.SuffixValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 正则校验
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PatternValidtor.class})
@Documented
public @interface PatternAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    String regexp() default "";
}
