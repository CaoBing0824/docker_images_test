package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2019-02-21 10:58:17
 */
@TableName("t_open_white_tips_info")
public class TOpenWhiteTipsInfo extends BaseEntity<TOpenWhiteTipsInfo> {

	/**
	* 定义表列名的常量
	*/
		//自增id
	public static String TB_ID = "id";
		//version
	public static String TB_VERSION = "version";
		//xyCode
	public static String TB_XY_CODE = "xy_code";
		//是否白名单验收 0否 1是
	public static String TB_WHITE_TIPS = "white_tips";
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
	
	@Override
    protected Serializable pkVal() {
        return null;
    }
	
	    //自增id
    
    private Long id;
	
	    //version

    private String version;
	
	    //xyCode

    private String xyCode;
	
	    //是否白名单验收 0否 1是

    private Integer whiteTips;
	
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
	 * 设置：version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：xyCode
	 */
	public void setXyCode(String xyCode) {
		this.xyCode = xyCode;
	}
	/**
	 * 获取：xyCode
	 */
	public String getXyCode() {
		return xyCode;
	}
	/**
	 * 设置：是否白名单验收 0否 1是
	 */
	public void setWhiteTips(Integer whiteTips) {
		this.whiteTips = whiteTips;
	}
	/**
	 * 获取：是否白名单验收 0否 1是
	 */
	public Integer getWhiteTips() {
		return whiteTips;
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
