package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.EnumValidAnnotation;
import com.xy.boot.open.model.annotation.IntegerAnnotation;
import com.xy.boot.open.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IntegerValidtor implements ConstraintValidator<IntegerAnnotation, String> {

	@Override
	public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
		if (com.xy.boot.common.util.StringUtils.isEmpty(str)) {
			return true;
		}
		if(StringUtils.isInteger(str)){
			return true;
		}
		return false;
	}
}
