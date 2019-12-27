package com.xy.boot.open.model.bo;

public class CheckInfoQueryBo {
    /**
     * 审核备注
     */
    private String remark;

    /**
     * 审核状态
     */
    private String status;

    /**
     * 审核状态说明
     */
    private String statusStr;

    /**
     * 小源企业编码
     */
    private String xyCode;

    /**
     * 小源数据版本
     */
    private String xyVersion;

    /**
     * 数据类型
     */
    private String dataType;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getXyCode() {
        return xyCode;
    }

    public void setXyCode(String xyCode) {
        this.xyCode = xyCode;
    }

    public String getXyVersion() {
        return xyVersion;
    }

    public void setXyVersion(String xyVersion) {
        this.xyVersion = xyVersion;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
