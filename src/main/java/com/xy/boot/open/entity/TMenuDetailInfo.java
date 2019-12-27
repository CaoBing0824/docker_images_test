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
@TableName("t_open_api_menu_detail_info")
public class TMenuDetailInfo extends BaseEntity<TMenuDetailInfo> {

    /**
     * 定义表列名的常量
     */
    //主键
    public static final String TB_ID = "id";
    //菜单项编码
    public static final String TB_ITEM_CODE = "item_code";
    //菜单项名称
    public static final String TB_ITEM_NAME = "item_name";
    //菜单项描述
    public static final String TB_ITEM_DESC = "item_desc";
    //菜单项类型
    public static final String TB_ITEM_TYPE = "item_type";
    //菜单项类型值
    public static final String TB_ITEM_TYPE_VAL = "item_type_val";
    //扩展
    public static final String TB_EXTEND_VAL = "extend_val";
    //菜单编码
    public static final String TB_XY_CODE = "xy_code";
    //版本号
    public static final String TB_VERSION = "version";
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

    //主键

    private Long id;

    //菜单项编码

    private String itemCode;

    //菜单项名称

    private String itemName;

    //菜单项描述

    private String itemDesc;

    //菜单项类型

    private String itemType;

    //菜单项类型值

    private String itemTypeVal;

    //菜单编码

    private String xyCode;

    //版本号

    private String version;

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
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：菜单项编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取：菜单项编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置：菜单项名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取：菜单项名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置：菜单项描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 获取：菜单项描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 设置：菜单项类型
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * 获取：菜单项类型
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 设置：菜单项类型值
     */
    public void setItemTypeVal(String itemTypeVal) {
        this.itemTypeVal = itemTypeVal;
    }

    /**
     * 获取：菜单项类型值
     */
    public String getItemTypeVal() {
        return itemTypeVal;
    }

    /**
     * 设置：菜单编码
     */
    public void setXyCode(String xyCode) {
        this.xyCode = xyCode;
    }

    /**
     * 获取：菜单编码
     */
    public String getXyCode() {
        return xyCode;
    }

    /**
     * 设置：版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取：版本号
     */
    public String getVersion() {
        return version;
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


    /**
     * @param itemCode
     * @param itemName
     * @param itemType
     * @param itemTypeVal
     * @param xyCode
     * @param version
     */
    public TMenuDetailInfo(final String itemCode, final String itemName, final String itemType, final String itemTypeVal, final String xyCode, final String version) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemTypeVal = itemTypeVal;
        this.xyCode = xyCode;
        this.version = version;
    }
}
