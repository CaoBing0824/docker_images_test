package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.MuiltDataValidtor;
import com.xy.boot.open.model.annotation.validator.SuffixValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 多值有效判断注解
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MuiltDataValidtor.class})
@Documented
public @interface MuiltDataValidAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};
    /**
     * 单个值最大字符数
     *
     * @return
     */
    int strlength() default 0;
}
