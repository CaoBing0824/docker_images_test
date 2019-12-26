package com.xy.boot.open.entity.enums;

/**
 * 通道号状态
 */
public enum ChannelNumStatusEnum {

    ENABLE(1, "启用"),
    VAILD_PASS(2, "验证通过"),
    OFFLINE_ONLINE(0, "户部下线,脚本上线"),
    VALIDATING(3, "验证中"),
    OFFLINE(-1, "下线"),
    NO_VALIDATION(-2, "未验证");


    private int code;
    private String desc;

    ChannelNumStatusEnum(int theCode, String theDesc){
        this.code = theCode;
        this.desc = theDesc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
