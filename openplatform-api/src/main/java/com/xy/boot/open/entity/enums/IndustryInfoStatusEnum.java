package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 行业允许黄页接口上报的状态
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-28 上午 10:54
 */
public enum IndustryInfoStatusEnum implements IEnum {

    NO_PERMISSION_YELLOW_REPORT(0, "不允许黄页上报"),
    PERMISSION_YELLOW_REPORT(1, "允许黄页上报");

    private final Integer statusCode;
    private final String statusDesc;

    IndustryInfoStatusEnum(Integer statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    @Override
    public Serializable getValue() {
        return this.getStatusCode();
    }
}
