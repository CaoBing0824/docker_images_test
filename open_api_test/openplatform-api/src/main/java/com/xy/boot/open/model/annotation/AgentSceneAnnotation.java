package com.xy.boot.open.model.annotation;

import com.xy.boot.open.model.annotation.validator.AgentSceneValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 代理商情景判断
 *
 * @author Ray
 *         Date:2019-01-08.
 **/

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgentSceneValidtor.class})
@Documented
public @interface AgentSceneAnnotation {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?>[] target() default {};
}
