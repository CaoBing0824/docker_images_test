package com.xy.boot.open.model.enums;

/**
 * 接口操作类型
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-05 下午 07:13
 */
public enum OperateTypeEnum {


    ADD("ADD", "增加"),
    MODIFY("MODIFY", "修改"),
    CANCEL("CANCEL", "取消");

    private final String type;
    private final String desc;

    public static OperateTypeEnum getDataTypeEnumByType(String type){
        for (OperateTypeEnum dataTypeEnum: OperateTypeEnum.values()) {
            if(type.equals(dataTypeEnum.getType())){
                return dataTypeEnum;
            }
        }
        return null;
    }

    OperateTypeEnum(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }




}
