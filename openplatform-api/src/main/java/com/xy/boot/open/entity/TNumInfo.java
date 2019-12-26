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
@TableName("t_open_api_num_info")
public class TNumInfo extends BaseEntity<TNumInfo> {

    /**
     * 定义表列名的常量
     */
    //主键ID
    public static final String TB_ID = "id";
    //公众号编码
    public static final String TB_XY_PUB_CODE = "xy_pub_code";
    //接入码
    public static final String TB_RECEIVE_NUM = "receive_num";
    //区域关联ID
    public static final String TB_AREAS_RELATION_ID = "areas_relation_id";
    //签名关联ID
    public static final String TB_SIGNATURES_RELATION_ID = "signatures_relation_id";
    //最小长度(用于标识号段所包含位数，若是固定位数，则最小值=最大值)
    public static final String TB_MIN_LEN = "min_len";
    //最大长度
    public static final String TB_MAX_LEN = "max_len";
    //用途
    public static final String TB_PURPOSE = "purpose";
    //扩展参数,保留字段
    public static final String TB_EXT_MAP = "ext_map";
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
    //是否企业专属号码
    public static final String TB_NUM_EXCLUSIVE = "numExclusive";


    @Override
    protected Serializable pkVal() {
        return null;
    }

    //主键ID

    private Long id;

    //公众号编码

    private String xyPubCode;

    //接入码

    private String receiveNum;

    //区域关联ID

    private Long areasRelationId;

    //签名关联ID

    private Long signaturesRelationId;

    //最小长度(用于标识号段所包含位数，若是固定位数，则最小值=最大值)

    private Integer minLen;

    //最大长度

    private Integer maxLen;

    //用途

    private String purpose;

    //扩展参数,保留字段

    private String extMap;

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

    //是否企业专属号码
    private String numExclusive;


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
     * 设置：接入码
     */
    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    /**
     * 获取：接入码
     */
    public String getReceiveNum() {
        return receiveNum;
    }

    /**
     * 设置：区域关联ID
     */
    public void setAreasRelationId(Long areasRelationId) {
        this.areasRelationId = areasRelationId;
    }

    /**
     * 获取：区域关联ID
     */
    public Long getAreasRelationId() {
        return areasRelationId;
    }

    /**
     * 设置：签名关联ID
     */
    public void setSignaturesRelationId(Long signaturesRelationId) {
        this.signaturesRelationId = signaturesRelationId;
    }

    /**
     * 获取：签名关联ID
     */
    public Long getSignaturesRelationId() {
        return signaturesRelationId;
    }

    /**
     * 设置：最小长度(用于标识号段所包含位数，若是固定位数，则最小值=最大值)
     */
    public void setMinLen(Integer minLen) {
        this.minLen = minLen;
    }

    /**
     * 获取：最小长度(用于标识号段所包含位数，若是固定位数，则最小值=最大值)
     */
    public Integer getMinLen() {
        return minLen;
    }

    /**
     * 设置：最大长度
     */
    public void setMaxLen(Integer maxLen) {
        this.maxLen = maxLen;
    }

    /**
     * 获取：最大长度
     */
    public Integer getMaxLen() {
        return maxLen;
    }

    /**
     * 设置：用途
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * 获取：用途
     */
    public String getPurpose() {
        return purpose;
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

    public String getNumExclusive() {
        return numExclusive;
    }

    public void setNumExclusive(String numExclusive) {
        this.numExclusive = numExclusive;
    }
}
