package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;
import com.xy.boot.open.entity.enums.ProductTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;


/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:51:05
 */
@TableName("t_open_api_pub_info")
public class TPubInfo extends BaseEntity<TPubInfo> {

    /**
     * 定义表列名的常量
     */
    //主键ID
    public static final String TB_ID = "id";
    //企业编码
    public static final String TB_XY_ENT_CODE = "xy_ent_code";
    //公众号名称
    public static final String TB_PUB_NAME = "pub_name";
    //白底logo图片的资源地址
    public static final String TB_SHOW_LOGO_WHITE = "show_logo_white";
    //彩底logo图片的资源地址
    public static final String TB_SHOW_LOGO_COLOR = "show_logo_color";
    //扩展参数,保留字段
    public static final String TB_EXT_MAP = "ext_map";
    //产品类型
    public static final String TB_PRODUCT_TYPE = "product_type";
    //数据版本，数据生成时间戳
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

    //合作月
    public static final String TB_COOPERATIVE_MONTH = "cooperative_month";

    @Override
    protected Serializable pkVal() {
        return null;
    }

    //主键ID

    private Long id;

    //企业编码

    private String xyEntCode;

    //公众号名称

    private String pubName;

    //白底logo图片的资源地址

    private String showLogoWhite;

    //彩底logo图片的资源地址

    private String showLogoColor;

    //扩展参数,保留字段

    private String extMap;

    //产品类型

    private ProductTypeEnum productType;

    //数据版本，数据生成时间戳

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

    // 行业
    private String industryCode;


    // 合作月 默认为12
    private int cooperativeMonth = 12;

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
     * 设置：企业编码
     */
    public void setXyEntCode(String xyEntCode) {
        this.xyEntCode = xyEntCode;
    }

    /**
     * 获取：企业编码
     */
    public String getXyEntCode() {
        return xyEntCode;
    }

    /**
     * 设置：公众号名称
     */
    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    /**
     * 获取：公众号名称
     */
    public String getPubName() {
        return pubName;
    }

    /**
     * 设置：白底logo图片的资源地址
     */
    public void setShowLogoWhite(String showLogoWhite) {
        this.showLogoWhite = showLogoWhite;
    }

    /**
     * 获取：白底logo图片的资源地址
     */
    public String getShowLogoWhite() {
        return showLogoWhite;
    }

    /**
     * 设置：彩底logo图片的资源地址
     */
    public void setShowLogoColor(String showLogoColor) {
        this.showLogoColor = showLogoColor;
    }

    /**
     * 获取：彩底logo图片的资源地址
     */
    public String getShowLogoColor() {
        return showLogoColor;
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
     * 设置：产品类型
     */
    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }

    /**
     * 获取：产品类型
     */
    public ProductTypeEnum getProductType() {
        return productType;
    }

    /**
     * 设置：数据版本，数据生成时间戳
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取：数据版本，数据生成时间戳
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

    /**
     * 设置：行业
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 获取：行业
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    /**
     * 获取：合作月
     */
    public int getCooperativeMonth() {
        return cooperativeMonth;
    }

    /**
     * 设置：合作月
     */
    public void setCooperativeMonth(int cooperativeMonth) {
        this.cooperativeMonth = cooperativeMonth;
    }
}
