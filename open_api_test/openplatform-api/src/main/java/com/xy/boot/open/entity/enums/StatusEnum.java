package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 数据状态
 *
 * @Author: tangchao@mfexcel.com
 * @Date: 2019-01-02 上午 10:10
 */
public enum StatusEnum implements IEnum {

    INVALID(0, "无效数据"),
    VALID(1, "有效数据");

    private final Integer statusCode;
    private final String statusDesc;

    StatusEnum(final Integer statusCode, final String statusDesc) {
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
        return this.statusCode;
    }
}
