package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.MuiltDataValidSizeAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 多值校验器,判断个数
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class MuiltDataSizeValidtor implements ConstraintValidator<MuiltDataValidSizeAnnotation, String> {

    int listsize;

    @Override
    public void initialize(MuiltDataValidSizeAnnotation annotation) {
        listsize = annotation.listsize();
    }

    @Override
    public boolean isValid(String datas, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(datas)) {
            return false;
        }
        String[] signs = datas.split(SysConstVar.ENG_REDNIK);
        if (signs != null && signs.length > 0) {
            if (signs.length > listsize) {
                return false;
            }
        }
        return true;
    }

}
