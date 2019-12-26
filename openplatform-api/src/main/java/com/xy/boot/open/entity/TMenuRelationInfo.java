package com.xy.boot.open.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;
import com.xy.boot.open.entity.enums.StatusEnum;


/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:51:05
 */
@TableName("t_open_api_menu_relation_info")
public class TMenuRelationInfo extends BaseEntity<TMenuRelationInfo> {

    /**
     * 定义表列名的常量
     */
    //自增id
    public static final String TB_ID = "id";
    //代理商ID
    public static final String TB_XY_AGENT_CODE = "xy_agent_code";
    //公众号编码
    public static final String TB_XY_PUB_CODE = "xy_pub_code";
    //号码编码
    public static final String TB_XY_NUM_CODE = "xy_num_code";
    //菜单编码
    public static final String TB_XY_MENU_CODE = "xy_menu_code";
    //代理商用户标识
    public static final String TB_AG_PHONEID = "ag_phoneId";
    //区域
    public static final String TB_AREA = "areasRelationId";
    //扩展参数,保留字段
    public static final String TB_EXT_MAP = "ext_map";
    //数据版本，数据生成时间
    public static final String TB_VERSION = "version";
    //业务编码
    public static final String TB_XY_CODE = "xy_code";
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

    //自增id

    private Long id;

    //代理商ID

    private String xyAgentCode;

    //公众号编码

    private String xyPubCode;

    //号码编码

    private String xyNumCode;

    //菜单编码

    private String xyMenuCode;

    //代理商用户标识

    private String agPhoneid;

    //区域

    private Long areasRelationId;

    //扩展参数,保留字段

    private String extMap;

    //数据版本，数据生成时间

    private String version;

    //业务编码

    private String xyCode;

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
     * 设置：自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：代理商编码
     */
    public void setXyAgentCode(String xyAgentCode) {
        this.xyAgentCode = xyAgentCode;
    }

    /**
     * 获取：代理商编码
     */
    public String getXyAgentCode() {
        return xyAgentCode;
    }

    /**
     * 设置：公众号编码
     */
    public void setXyPubCode(String xyPubCode) {
        this.xyPubCode = xyPubCode;
    }

    /**
     * 获取：公众号编码
     */
    public String getXyPubCode() {
        return xyPubCode;
    }

    /**
     * 设置：号码编码
     */
    public void setXyNumCode(String xyNumCode) {
        this.xyNumCode = xyNumCode;
    }

    /**
     * 获取：号码编码
     */
    public String getXyNumCode() {
        return xyNumCode;
    }

    /**
     * 设置：菜单编码
     */
    public void setXyMenuCode(String xyMenuCode) {
        this.xyMenuCode = xyMenuCode;
    }

    /**
     * 获取：菜单编码
     */
    public String getXyMenuCode() {
        return xyMenuCode;
    }

    /**
     * 设置：代理商用户标识
     */
    public void setAgPhoneid(String agPhoneid) {
        this.agPhoneid = agPhoneid;
    }

    /**
     * 获取：代理商用户标识
     */
    public String getAgPhoneid() {
        return agPhoneid;
    }

    /**
     * 设置：区域
     */
    public void setAreasRelationId(Long areasRelationId) {
        this.areasRelationId = areasRelationId;
    }

    /**
     * 获取：区域
     */
    public Long getAreasRelationId() {
        return areasRelationId;
    }

    /**
     * 设置：扩展参数,保留字段
     */
    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

    /**
     * 获取：扩展参数,保留字段
     */
    public String getExtMap() {
        return extMap;
    }

    /**
     * 设置：数据版本，数据生成时间
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取：数据版本，数据生成时间
     */
    public String getVersion() {
        return version;
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
