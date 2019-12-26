package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.model.annotation.AreasValidAnnotation;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.util.AreaUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 区域校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
public class AreasValidtor implements ConstraintValidator<AreasValidAnnotation, String> {
    /**
     * 是否判断空,默认判断空
     */
    int checkEmpty;

    @Override
    public boolean isValid(String areas, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(areas)) {
            //判断是否全国,包含全国时只有是全国
            if (areas.contains(SysConstVar.CN_AREA_CODE)) {
                if (SysConstVar.CN_AREA_CODE.equals(areas.replace(SysConstVar.ENG_REDNIK, ""))) {
                    return true;
                } else {
                    return false;
                }
            }
            //按 ; 分隔，判断有效值
            String[] split = areas.split(SysConstVar.ENG_REDNIK);
            if (split == null || split.length == 0) {
                return false;
            }
            if (AreaUtil.contains(Arrays.asList(split))) {
                return true;
            }
        } else {
            if (StatusEnum.INVALID.getStatusCode() == checkEmpty) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(final AreasValidAnnotation constraintAnnotation) {
        checkEmpty = constraintAnnotation.checkEmpty();
    }
}
