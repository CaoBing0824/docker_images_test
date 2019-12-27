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
 * @date 2019-01-08 16:36:48
 */
@TableName("t_open_scene_list")
public class TOpenSceneList extends BaseEntity<TOpenSceneList> {

	/**
	* 定义表列名的常量
	*/
		//主键ID
	public static final String TB_ID = "id";
		//情景名称
	public static final String TB_SCENE_NAME = "scene_name";
		//情景编码
	public static final String TB_SCENE_CODE = "scene_code";
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
	
	    //情景名称

    private String sceneName;
	
	    //情景编码

    private String sceneCode;
	
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
	 * 设置：情景名称
	 */
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	/**
	 * 获取：情景名称
	 */
	public String getSceneName() {
		return sceneName;
	}
	/**
	 * 设置：情景编码
	 */
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	/**
	 * 获取：情景编码
	 */
	public String getSceneCode() {
		return sceneCode;
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
