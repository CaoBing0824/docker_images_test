package com.xy.boot.open.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.StatusEnum;


/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:51:05
 */
@TableName("t_open_api_yellow_info")
public class TYellowInfo extends BaseEntity<TYellowInfo> {

    /**
     * 定义表列名的常量
     */
    //自增id
    public static final String TB_ID = "id";
    //公众号编码
    public static final String TB_XY_PUB_CODE = "xy_pub_code";
    //企业口号
    public static final String TB_SLOGAN = "slogan";
    //企业介绍
    public static final String TB_INTRODUCTION = "introduction";
    //服务热线
    public static final String TB_TELL_PHONE = "tell_phone";
    //企业地址
    public static final String TB_ADDRESS = "address";
    //企业搜索关键词
    public static final String TB_SERCH_KEY = "serch_key";
    //经营范围
    public static final String TB_RANGE = "range";
    //营业时间
    public static final String TB_OPEN_TIME = "open_time";
    //经纬度
    public static final String TB_LONLAT = "lonlat";
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

    //公众号编码

    private String xyPubCode;

    //企业口号

    private String slogan;

    //企业介绍

    private String introduction;

    //服务热线

    private String tellPhone;

    //企业地址

    private String address;

    //企业搜索关键词

    private String serchKey;

    //经营范围

    private String range;

    //营业时间

    private String openTime;

    //经纬度

    private String lonlat;

    //扩展参数,保留字段

    private String extMap;

    //数据版本，数据生成时间

    private String version;

    //业务编码

    private String xyCode;

    //状态

    private Integer status;

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
     * 设置：企业口号
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    /**
     * 获取：企业口号
     */
    public String getSlogan() {
        return slogan;
    }
    /**
     * 设置：企业介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    /**
     * 获取：企业介绍
     */
    public String getIntroduction() {
        return introduction;
    }
    /**
     * 设置：服务热线
     */
    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }
    /**
     * 获取：服务热线
     */
    public String getTellPhone() {
        return tellPhone;
    }
    /**
     * 设置：企业地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 获取：企业地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：企业搜索关键词
     */
    public void setSerchKey(String serchKey) {
        this.serchKey = serchKey;
    }
    /**
     * 获取：企业搜索关键词
     */
    public String getSerchKey() {
        return serchKey;
    }
    /**
     * 设置：经营范围
     */
    public void setRange(String range) {
        this.range = range;
    }
    /**
     * 获取：经营范围
     */
    public String getRange() {
        return range;
    }
    /**
     * 设置：营业时间
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    /**
     * 获取：营业时间
     */
    public String getOpenTime() {
        return openTime;
    }
    /**
     * 设置：经纬度
     */
    public void setLonlat(String lonlat) {
        this.lonlat = lonlat;
    }
    /**
     * 获取：经纬度
     */
    public String getLonlat() {
        return lonlat;
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
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取：状态
     */
    public Integer getStatus() {
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
