package com.xy.boot.open.util;

import com.xy.boot.open.model.enums.OperateTypeEnum;

/**
 * 参数判断,type=ADD，xyEntCode为空时，提示改为，新增时，xyEntCode要为空
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-07.
 **/
public class ParamUtils {
    /**
     * 判断Code和类型
     *
     * @param xyCode
     * @param type
     * @@return msg 提示信息
     */
    public static String validCodeAndType(String xyCode, String type, String typeName) {
        String msg = "";
        if (OperateTypeEnum.ADD.getType().equalsIgnoreCase(type) && !org.springframework.util.StringUtils.isEmpty(xyCode)) {
            msg = "类型为新增时,"+typeName+"编码需为空";
        }
        if (OperateTypeEnum.MODIFY.getType().equalsIgnoreCase(type) && org.springframework.util.StringUtils.isEmpty(xyCode)) {
            msg = "类型为修改时,"+typeName+"编码需不为空";
        }
        if(OperateTypeEnum.CANCEL.getType().equalsIgnoreCase(type)){
            msg = "操作类型错误";
        }
        return msg;
    }
}
