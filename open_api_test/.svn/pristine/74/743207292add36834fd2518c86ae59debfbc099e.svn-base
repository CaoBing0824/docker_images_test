package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 子菜单动动作类型
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-08 上午 11:32
 */
public enum SubMenuActionTypeEnum implements IEnum {
    SEND_SMS("SEND_SMS", "发送短信"),
    OPEN_URL("OPEN_URL", "打开URL"),
    CALL("CALL", "拨打电话"),
    OPEN_APP("OPEN_APP", "打开APP"),
    OPEN_FAST("OPEN_FAST", "打开快应用");

    private final String type;
    private final String desc;

    SubMenuActionTypeEnum(final String type, final String desc) {
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
    public static SubMenuActionTypeEnum getEnum(String type) {
        for (SubMenuActionTypeEnum item : SubMenuActionTypeEnum.values()) {
            if (type.equals(item.getType())) {
                return item;
            }
        }
        return null;
    }
    }

