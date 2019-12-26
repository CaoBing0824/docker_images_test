package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

@TableName("t_open_api_pub_renewal_data")
public class TOpenRenewalInfo extends BaseEntity<TOpenRenewalInfo> {

    /**
     * 公众号编码
     */
    @TableField("xy_code")
    private String xyCode;

    /**
     * 业务编号
     */
    @TableField("biz_code")
    private String bizCode;

    /**
     * 业务发起时间
     */
    @TableField("biz_start_time")
    private Date bizStartTime;

    /**
     * 原始到期时间
     */
    @TableField("org_expiry_date")
    private Date orgExpiryDate;

    /**
     * 新到期时间
     */
    @TableField("new_expiry_date")
    private Date newExpiryDate;

    /**
     * 业务结果
     */
    @TableField("biz_result")
    private String bizResult;

    /**
     * 业务结果描述
     */
    @TableField("biz_desc")
    private String bizDesc;

    /**
     * 审核人
     */
    @TableField("auditor")
    private String auditor;

    /**
     * 审核日期
     */
    @TableField("audit_date")
    private Date auditDate;

    /**
     * 数据状态
     */
    @TableField("status")
    private int status = 1;//默认有效


    /**
     * 创建时间
     */
    @TableField("created")
    private Date created;

    /**
     * 修改时间
     */
    @TableField("updated")
    private Date updated;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 修改人
     */
    @TableField("updated_by")
    private String updatedBy;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getXyCode() {
        return xyCode;
    }

    public void setXyCode(String xyCode) {
        this.xyCode = xyCode;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Date getBizStartTime() {
        return bizStartTime;
    }

    public void setBizStartTime(Date bizStartTime) {
        this.bizStartTime = bizStartTime;
    }

    public Date getOrgExpiryDate() {
        return orgExpiryDate;
    }

    public void setOrgExpiryDate(Date orgExpiryDate) {
        this.orgExpiryDate = orgExpiryDate;
    }

    public Date getNewExpiryDate() {
        return newExpiryDate;
    }

    public void setNewExpiryDate(Date newExpiryDate) {
        this.newExpiryDate = newExpiryDate;
    }

    public String getBizResult() {
        return bizResult;
    }

    public void setBizResult(String bizResult) {
        this.bizResult = bizResult;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
