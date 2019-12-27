package com.xy.boot.open.model.annotation;


import com.xy.boot.open.model.annotation.validator.IndustryCodeValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * XyCode有效性注解
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IndustryCodeValidtor.class})
@Documented
public @interface IndustryCodeValidAnnotation {
    String message() default "行业编码不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

}
