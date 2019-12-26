package com.xy.boot.open.model.annotation.validator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.model.annotation.JsonAnnotation;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * json格式校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class JsonValidtor implements ConstraintValidator<JsonAnnotation, String> {
    /**
     * 是否判断空,默认判断空
     */
    int checkEmpty;

    @Override
    public boolean isValid(String json, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(json)) {
            try {
                JSONObject jsonObject = JSON.parseObject(json);
                if (StatusEnum.VALID.getStatusCode() == checkEmpty) {
                    for (Map.Entry entry : jsonObject.entrySet()) {
                        return true;
                    }
                } else {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            if (StatusEnum.INVALID.getStatusCode() == checkEmpty) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(final JsonAnnotation constraintAnnotation) {
        checkEmpty = constraintAnnotation.checkEmpty();
    }
}
