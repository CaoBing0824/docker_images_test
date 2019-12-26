package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

@TableName("t_open_api_brand_data")
public class TBrandInfo extends BaseEntity<TBrandInfo> {

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 品牌代码
     */
    @TableField("brand_code")
    private String brandCode;

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
     * 更新时间
     */
    @TableField("updated")
    private Date updated;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
