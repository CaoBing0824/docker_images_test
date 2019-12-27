package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.SuffixValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 后缀有效判断注解，用于资源文件
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SuffixValidtor.class})
@Documented
public @interface SuffixValidAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    String suffix() default "";
}
