package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 数据类型
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-28 上午 10:54
 */
public enum ProductTypeEnum implements IEnum {
    PP("PP", "品宣"),
    YELLOW("YELLOW", "黄页"),
    SMSP("SMSP", "短信门户"),
    MENU("MENU", "菜单"),
    CU_MENU("CU_MENU", "定制菜单（千人千面）");

    private final String type;
    private final String desc;

    ProductTypeEnum(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public Serializable getValue() {
        return this.type;
    }

    /**
     * 根据type获取枚举类
     *
     * @param type
     * @return
     */
    public static ProductTypeEnum getEnum(String type) {
        for (ProductTypeEnum item : ProductTypeEnum.values()) {
            if (type.equals(item.getType())) {
                return item;
            }
        }
        return null;
    }
}
