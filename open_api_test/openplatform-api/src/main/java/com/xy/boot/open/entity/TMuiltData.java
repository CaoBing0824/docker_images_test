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
@TableName("t_open_api_muilt_data")
public class TMuiltData extends BaseEntity<TMuiltData> {

	/**
	 * 定义表列名的常量
	 */
	//自增id
	public static final String TB_ID = "id";
	//数据关联Id
	public static final String TB_RELATION_ID = "relation_id";
	//值
	public static final String TB_VALUE = "value";
	//数据类型
	public static final String TB_TYPE = "type";
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

	//数据关联Id
	private Long relationId;

	//值
	private String value;

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
	 * 设置：数据关联Id
	 */
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	/**
	 * 获取：数据关联Id
	 */
	public Long getRelationId() {
		return relationId;
	}

	/**
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
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
