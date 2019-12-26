package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.DateTimeAfterNowAnnotation;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 有效时间校验器
 * 判断时间是否大于当前时间
 * 日期为空返回 true
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class DateTimeAfterNowValidtor implements ConstraintValidator<DateTimeAfterNowAnnotation, String> {

    @Override
    public boolean isValid(String dateTime, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(dateTime)) {
            try {
                DateTime parse = DateTime.parse(dateTime);
                if (!parse.isAfterNow()) {
                    return false;
                }
            } catch (Exception e) {

            }
        }
        return true;
    }
}
