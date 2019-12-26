package com.xy.boot.open.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;


/**
 * 
 * 
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2019-02-21 10:08:09
 */
@TableName("t_open_check_data")
public class TOpenCheckData extends BaseEntity<TOpenCheckData> {

	/**
	* 定义表列名的常量
	*/
		//自增id
	public static String TB_ID = "id";
		//版本
	public static String TB_VERSION = "version";
		//业务编码
	public static String TB_XY_CODE = "xy_code";
		//标识编码（如子菜单审核适用）
	public static String TB_SUB_CODE = "sub_code";
		//审核状态
	public static String TB_CHECK_STATUS = "check_status";
		//审核人
	public static String TB_CHECK_USER = "check_user";
		//备注
	public static String TB_REMARK = "remark";
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
	
	    //版本

    private String version;
	
	    //业务编码

    private String xyCode;
	
	    //标识编码（如子菜单审核适用）

    private String subCode;
	
	    //审核状态

    private Integer checkStatus;
	
	    //审核人

    private String checkUser;
	
	    //备注

    private String remark;
	
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
	 * 设置：版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本
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
	 * 设置：标识编码（如子菜单审核适用）
	 */
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	/**
	 * 获取：标识编码（如子菜单审核适用）
	 */
	public String getSubCode() {
		return subCode;
	}
	/**
	 * 设置：审核状态
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}
	/**
	 * 设置：审核人
	 */
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	/**
	 * 获取：审核人
	 */
	public String getCheckUser() {
		return checkUser;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
