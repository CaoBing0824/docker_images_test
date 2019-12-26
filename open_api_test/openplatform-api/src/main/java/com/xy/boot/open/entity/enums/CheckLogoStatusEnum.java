package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * Logo审核状态码对应信息
 *
 * @author maisenlin@mfexcel.com
 * @since  2019-05-27
 */
public enum CheckLogoStatusEnum implements IEnum {

    LOGO_STATUS_IGNORE(0, "不需要审核"),
    LOGO_STATUS_WAIT_REVIEW(1, "待审核"),
    LOGO_STATUS_AUDIT_PASSED(2, "审核通过"),
    LOGO_STATUS_AUDIT_NO_PASS(3, "审核不通过");

    private final int statusCode;

    private final String statusDesc;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    CheckLogoStatusEnum(int statusCode, String statusDesc){
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    @Override
    public Serializable getValue() {
        return this.statusCode;
    }
}
