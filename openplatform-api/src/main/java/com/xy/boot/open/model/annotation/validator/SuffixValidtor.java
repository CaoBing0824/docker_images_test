package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.SuffixValidAnnotation;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 后缀校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class SuffixValidtor implements ConstraintValidator<SuffixValidAnnotation, String> {
    String suffix;

    @Override
    public void initialize(SuffixValidAnnotation annotation) {
        suffix = annotation.suffix();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(suffix)) {
            String[] params = suffix.split(",");
            if (null != params) {
                boolean result = false;
                for (int i = 0; i < params.length; i++) {
                    if (!StringUtils.isEmpty(params[i])) {
                        if (name.toUpperCase().endsWith(params[i].toUpperCase())) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }
        }
        return true;
    }
}
