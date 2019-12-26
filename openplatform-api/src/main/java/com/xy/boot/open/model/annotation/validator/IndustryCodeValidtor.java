package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.IndustryCodeValidAnnotation;
import com.xy.boot.open.service.IOpenIndustryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 行业编码校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class IndustryCodeValidtor implements ConstraintValidator<IndustryCodeValidAnnotation, String> {


    @Autowired
    private IOpenIndustryInfoService iOpenIndustryInfoService;

    @Override
    public boolean isValid(String industryCode, ConstraintValidatorContext context) {
        if ( !StringUtils.isEmpty(industryCode) ) {
            return iOpenIndustryInfoService.existIndustryCode(industryCode);
        }
        return true;
    }
}
