package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;


/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:51:05
 */
@TableName("t_open_api_data_relation_release")
public class TDataRelationRelease extends BaseEntity<TDataRelationRelease> {

    /**
     * 定义表列名的常量
     */
    //主键ID
    public static final String TB_ID = "id";
    //业务编码
    public static final String TB_XY_CODE = "xy_code";
    //数据版本
    public static final String TB_VERSION = "version";
    //数据类型（企业信息、黄页等等）
    public static final String TB_DATA_TYPE = "data_type";
    //工单ID
    public static final String TB_WORKORDER_ID = "workorder_id";
    //审核状态
    public static final String TB_CHECK_STATUS = "check_status";
    //状态
    public static final String TB_STATUS = "status";
    //创建时间
    public static final String TB_CREATED = "created";
    //修改时间
    public static final String TB_UPDATED = "updated";
    //创建人
    public static final String TB_CREATED_BY = "created_by";
    //修改人
    public static final String TB_UPDATED_BY = "updated_by";

    @Override
    protected Serializable pkVal() {
        return null;
    }

    //主键ID

    private Long id;

    //业务编码

    private String xyCode;

    //数据版本

    private String version;

    //数据类型（企业信息、黄页等等）

    private DataTypeEnum dataType;

    //工单ID

    private Long workorderId;

    //审核状态

    private CheckStatusEnum checkStatus;

    //状态

    private StatusEnum status;

    //创建时间

    private Date created;

    //修改时间

    private Date updated;

    //创建人

    private String createdBy;

    //修改人

    private String updatedBy;



    /**
     * 设置：主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：业务编码
     */
    public void setXyCode(String xyCode) {
        this.xyCode = xyCode;
    }

    /**
     * 获取：业务编码
     */
    public String getXyCode() {
        return xyCode;
    }

    /**
     * 设置：数据版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取：数据版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置：数据类型（企业信息、黄页等等）
     */
    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取：数据类型（企业信息、黄页等等）
     */
    public DataTypeEnum getDataType() {
        return dataType;
    }

    /**
     * 设置：工单ID
     */
    public void setWorkorderId(Long workorderId) {
        this.workorderId = workorderId;
    }

    /**
     * 获取：工单ID
     */
    public Long getWorkorderId() {
        return workorderId;
    }

    /**
     * 设置：审核状态
     */
    public void setCheckStatus(CheckStatusEnum checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 获取：审核状态
     */
    public CheckStatusEnum getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置：状态
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * 设置：创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置：创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取：创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置：修改人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取：修改人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
}
