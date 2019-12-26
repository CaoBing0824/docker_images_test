package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 数据类型
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-28 上午 10:54
 */
public enum DataTypeEnum implements IEnum {

    ENT("ENT", "企业信息"),
    PUB("PUB", "公众号信息"),
    LOGO("LOGO", "LOGO信息"),
    CHANNEL("CHANNEL", "通道号信息"),
    YELLOW_PAGE("YELLOW_PAGE", "黄页信息"),
    MENU("MENU", "菜单信息"),
    MENU_RELATION("MENU_RELATION", "菜单关联信息"),
    BUBBLE("BUBBLE", "短信卡片信息"),
    BUBBLE_BTN("BUBBLE_BTN", "短信服务信息"),
    WHITE_LIST("WHITE_LIST", "白名单信息"),
    DEFAULT("DEFAULT", "DEFAULT");

    private final String type;
    private final String desc;

    DataTypeEnum(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static DataTypeEnum getDataTypeEnumByType(String type){
        for (DataTypeEnum dataTypeEnum: DataTypeEnum.values()) {
            if(type.equals(dataTypeEnum.getType())){
                return dataTypeEnum;
            }
        }
        return null;
    }

    @Override
    public Serializable getValue() {
        return this.getType();
    }
}
