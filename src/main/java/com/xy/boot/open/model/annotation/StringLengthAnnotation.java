package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.StringLengthValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 判断含中文的字串长度（一个汉字占两个字符长度）
 *
 * @author maisenlin@mfexcel.com
 *         Date:2019-05-24.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StringLengthValidtor.class})
@Documented
public @interface StringLengthAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    int maxLen() default 0;
}
