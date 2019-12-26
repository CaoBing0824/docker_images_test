package com.xy.boot.open.model.annotation.validator;

import com.xy.boot.open.model.annotation.EnumValidAnnotation;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 枚举类校验器
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
@Slf4j
public class EnumValidtor implements ConstraintValidator<EnumValidAnnotation, String> {
    Class<?>[] cls;

    @Override
    public void initialize(EnumValidAnnotation constraintAnnotation) {
        cls = constraintAnnotation.target();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        //枚举类验证
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("name");
                        for (Object obj : objs
                                ) {
                            Object code = method.invoke(obj, null);
                            if (null != code && null != value) {
                                if (value.equals(code.toString())) {
                                    return true;
                                }
                            }
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
