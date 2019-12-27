package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.NumRangeAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 号段格式校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class NumRangeValidtor implements ConstraintValidator<NumRangeAnnotation, String> {

    @Override
    public boolean isValid(String num, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(num)) {
            if (com.xy.boot.open.util.StringUtils.isNumeric(num.replaceAll(SysConstVar.ASTERISK_STR, ""))) {
                return true;
            } else {
                if (num.contains(SysConstVar.SPLIT_B)) {
                    String[] nums = num.split(SysConstVar.SPLIT_B);
                    if (nums.length == 2) {
                        boolean result = true;
                        for (int i = 0; i < nums.length; i++) {
                            if (StringUtils.isEmpty(nums[i]) || !com.xy.boot.open.util.StringUtils.isNumeric(nums[i].replaceAll("\\*", ""))) {
                                result = false;
                                break;
                            }
                        }
                        if (result) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
