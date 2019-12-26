package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2006-2019, xy
 * FileName:
 *
 * @author maisenlin@mfexcel.com
 * @Date 2019-06-25 12:18
 **/

@TableName("t_open_industry_list")
public class TOpenIndustryInfo extends BaseEntity<TOpenIndustryInfo> {

    /**
     * 定义表列名的常量
     */
    //自增id
    public static String TB_ID = "id";
    // 大类
    public static String TB_LARGE_CLASS = "large_class";
    // 小类
    public static String TB_SUB_CLASS = "sub_class";
    // 行业编码
    public static String TB_INDUSTRY_CODE = "industry_code";
    // 支持黄页接口上报
    public static String TB_PERMISSION_YELLOW_REPORT = "permission_yellow_report";
    //状态
    public static String TB_STATUS = "status";
    //创建时间
    public static String TB_CREATED = "created";
    //修改时间
    public static String TB_UPDATED = "updated";
    //创建人
    public static String TB_CREATED_BY = "created_by";
    //修改人
    public static String TB_UPDATED_BY = "updated_by";

    /** id*/
    @TableField("id")
    private Long id;

    /** 大类*/
    @TableField("large_class")
    private String largeClass;

    /** 小类*/
    @TableField("sub_class")
    private String subClass;

    /** 行业编码*/
    @TableField("industry_code")
    private String industryCode;

    /** 支持黄页接口上报：1 表示支持，0表示不支持*/
    @TableField("permission_yellow_report")
    private Integer permissionYellowReport;

    /** 状态*/
    @TableField("status")
    private Integer status;

    /** 创建时间*/
    @TableField("created")
    private Date created;

    /** 修改时间*/
    @TableField("updated")
    private Date updated;

    /** 创建人*/
    @TableField("created_by")
    private String createdBy;

    /** 修改人*/
    @TableField("updated_by")
    private String updatedBy;


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLargeClass() {
        return largeClass;
    }

    public void setLargeClass(String largeClass) {
        this.largeClass = largeClass;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public int getPermissionYellowReport() {
        return permissionYellowReport;
    }

    public void setPermissionYellowReport(int permissionYellowReport) {
        this.permissionYellowReport = permissionYellowReport;
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

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
