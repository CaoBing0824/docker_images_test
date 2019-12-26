package com.xy.boot.open.model.bo;

/**
 * Created by fuwenshen
 * Date:2018/11/13
 * Time:10:59
 * 用来封装签名信息（签名信息放在参数里面）
 */
public class SignInfoBO {


    /**
     * 请求标识
     */
    private String requestId;

    /**
     * 平台标识
     */
    private String appId;

    /**
     * 签名
     */
    private String sign;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
