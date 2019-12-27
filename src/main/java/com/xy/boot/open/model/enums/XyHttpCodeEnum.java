package com.xy.boot.open.model.enums;

/**
 * Created by fuwenshen.
 *
 * 接口状态参数通用规则
 *
 * 2000:表示成功
 * 1000:表示失败。
 * 3001: token已失效
 * 3002: 系统繁忙
 * 3003：非法请求
 * 3004: 非法签名
 * 3005: 已经处理
 * 3011: 请求太频繁
 * ----------------------------------------------------------------------------
 * 600-700 : 业务状态码
 * ----------------------------------------------------------------------------
 */
public enum XyHttpCodeEnum {



    OK(2000, "操作成功"),
    FAIL(1000, "操作失败"),
    TOKEN_INVALID(3001,"token已失效"),
    INVALID_SIGNATURE(3004, "非法签名"),
    ILLEGAL_REQUEST(3003, "非法请求"),
    SYSTEM_BUSY(3002, "系统繁忙"),

    /**
     * 中移返回成功状态
     */
    RESULT_OK(90001, "请求成功");



    private final int code;
    private final String message;

    XyHttpCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }


}
