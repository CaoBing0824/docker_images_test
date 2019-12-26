package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import com.xy.boot.open.service.IDataRelationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * XyCode校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class XyCodeValidtor implements ConstraintValidator<XyCodeValidAnnotation, String> {

    @Autowired
    private IDataRelationHistoryService iDataRelationHistoryService;

    private DataTypeEnum dateType;

    @Override
    public void initialize(XyCodeValidAnnotation constraintAnnotation) {
        dateType = constraintAnnotation.dataType();
    }

    @Override
    public boolean isValid(String xyCode, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(xyCode)) {
            if (iDataRelationHistoryService.checkXyCode(xyCode,dateType)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
