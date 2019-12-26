package com.xy.boot.open.model.annotation;


import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.validator.XyCodeValidtor;

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
@Constraint(validatedBy = {XyCodeValidtor.class})
@Documented
public @interface XyCodeValidAnnotation {
    String message() default "业务编码错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    DataTypeEnum dataType() default DataTypeEnum.DEFAULT;
}
