package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.NumRangeLengthAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 号段校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class NumRangeLengthValidtor implements ConstraintValidator<NumRangeLengthAnnotation, String> {

    int maxlength;

    @Override
    public void initialize(final NumRangeLengthAnnotation constraintAnnotation) {
        maxlength = constraintAnnotation.maxlength();
    }

    @Override
    public boolean isValid(String num, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(num)) {
            if (num.contains(SysConstVar.SPLIT_B)) {
                String[] nums = num.split(SysConstVar.SPLIT_B);
                for (int i = 0; i < nums.length; i++) {
                    if (!StringUtils.isEmpty(nums[i])) {
                        if (nums[i].length() > maxlength) {
                            return false;
                        }
                    }
                }
            } else {
                //判断单个号码情况
                if (num.length() > maxlength) {
                    return false;
                }
            }
        }
        return true;
    }
}
