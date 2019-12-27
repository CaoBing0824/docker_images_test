package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.PatternAnnotation;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 正则校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class PatternValidtor implements ConstraintValidator<PatternAnnotation, String> {
    String regexp;

    @Override
    public void initialize(PatternAnnotation annotation) {
        regexp = annotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(value) && !StringUtils.isEmpty(regexp)) {
            Pattern p = Pattern.compile(regexp);
            if (!p.matcher(value).matches()) {
                return false;
            }
        }
        return true;
    }
}
