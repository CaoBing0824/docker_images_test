package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.StringLengthAnnotation;
import com.xy.boot.open.util.StringLengthVaildUtil;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *  中文的字串长度（一个汉字占两个字符长度）检查器
 */
public class StringLengthValidtor implements ConstraintValidator<StringLengthAnnotation, String> {
    // 字符串长度
    int length;

    @Override
    public void initialize(StringLengthAnnotation annotation) {
        length = annotation.maxLen();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {

        if( StringUtils.isNotEmpty(str) ){
            return StringLengthVaildUtil.isLte(length, str);
        } else {
            return true;
        }

    }
}
