package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.MuiltDataValidAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 多值校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class MuiltDataValidtor implements ConstraintValidator<MuiltDataValidAnnotation, String> {

    int strlength;

    @Override
    public void initialize(MuiltDataValidAnnotation annotation) {
        strlength = annotation.strlength();
    }

    @Override
    public boolean isValid(String datas, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(datas)) {
            return false;
        }
        String[] signs = datas.split(SysConstVar.ENG_REDNIK);
        if (signs != null && signs.length > 0) {
            boolean check = true;
            for (int i = 0; i < signs.length; i++) {
                if (!StringUtils.isEmpty(signs[i]) && signs[i].length() > strlength) {
                    check = false;
                    break;
                }
            }
            return check;
        }
        return false;
    }

}
