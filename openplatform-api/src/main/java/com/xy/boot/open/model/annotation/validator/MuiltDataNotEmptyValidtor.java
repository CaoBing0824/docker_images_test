package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.MuiltDataNotEmptyValidAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 多值非空校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class MuiltDataNotEmptyValidtor implements ConstraintValidator<MuiltDataNotEmptyValidAnnotation, String> {


    @Override
    public boolean isValid(String datas, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(datas)) {
            return false;
        }
        String[] signs = datas.split(SysConstVar.ENG_REDNIK);
        if (signs != null && signs.length > 0) {
            return true;
        }
        return false;
    }

}
