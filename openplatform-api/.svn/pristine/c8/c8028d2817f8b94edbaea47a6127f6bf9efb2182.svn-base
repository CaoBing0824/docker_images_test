package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.constant.RegexConstant;
import com.xy.boot.open.model.annotation.LonlatValidAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 经纬度校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class LonlatValidtor implements ConstraintValidator<LonlatValidAnnotation, String> {


    @Override
    public boolean isValid(String lonlat, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(lonlat)) {
            String[] split = lonlat.split(SysConstVar.ENG_DOT);
            if (split == null || split.length != SysConstVar.LONLAT_SIZE) {
                return false;
            }

          /*  for (int i = 0; i < split.length; i++) {
                if (!com.xy.boot.open.util.StringUtils.isNumeric(split[i])) {
                    return false;
                }
            }*/

            // 格式判断
            if( !Pattern.matches(RegexConstant.REGEX_LONGITUDE, split[0] )
                    || !Pattern.matches(RegexConstant.REGEX_LATITUDE, split[1])){
                return false;
            }

        }
        return true;
    }
}
