package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author maisenlin@mfexcel.com
 * @since 2019-06-10
 */
@TableName("t_open_api_logo_info")
public class TOpenLogoInfo extends BaseEntity<TOpenLogoInfo> {

    /**
     * 定义表列名的常量
     */
    // 公众号编码
    public static final String TB_XY_PUB_CODE = "xy_pub_code";
    // 业务编码
    public static final String TB_XY_CODE = "xy_code";
    // 创建时间
    public static final String TB_CREATED = "created";

    /**
     * 公众号编码
     */
    @TableField("xy_pub_code")
    private String xyPubCode;

    /**
     * 白底logo图片的资源地址
     */
    @TableField("show_logo_white")
    private String showLogoWhite;

    /**
     * 彩底logo图片的资源地址
     */
    @TableField("show_logo_color")
    private String showLogoColor;

    /**
     * 扩展参数,保留字段
     */
    @TableField("ext_map")
    private String extMap;

    /**
     * 数据版本，数据生成时间戳
     */
    private String version;

    /**
     * 业务编码
     */
    @TableField("xy_code")
    private String xyCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
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

    public String getXyPubCode() {
        return xyPubCode;
    }

    public void setXyPubCode(String xyPubCode) {
        this.xyPubCode = xyPubCode;
    }

    public String getShowLogoWhite() {
        return showLogoWhite;
    }

    public void setShowLogoWhite(String showLogoWhite) {
        this.showLogoWhite = showLogoWhite;
    }

    public String getShowLogoColor() {
        return showLogoColor;
    }

    public void setShowLogoColor(String showLogoColor) {
        this.showLogoColor = showLogoColor;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getXyCode() {
        return xyCode;
    }

    public void setXyCode(String xyCode) {
        this.xyCode = xyCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
