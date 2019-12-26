package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.JsonValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * json有效判断注解
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {JsonValidtor.class})
@Documented
public @interface JsonAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    //是否判断空，默认判断json需要有值，如菜单配置项为空时传 {} checkEmpty = 0
    int checkEmpty() default 1;

}
