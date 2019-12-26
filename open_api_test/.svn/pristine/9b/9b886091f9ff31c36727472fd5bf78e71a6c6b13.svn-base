package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;
import com.xy.boot.open.entity.enums.ActionTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:51:05
 */
@TableName("t_open_api_action_info")
public class TActionInfo extends BaseEntity<TActionInfo> {

	/**
	* 定义表列名的常量
	*/
		//自增id
	public static final String TB_ID = "id";
		//代理商ID
	public static final String XY_PUB_CODE = "xy_pub_code";
	    //短信编码
	public static final String XY_SMS_CODE = "xy_sms_code";
		//情景编码
	public static final String SCENE_CODE = "scene_code";
		//按钮名称
	public static final String TB_BTN_NAME = "btn_name";
		//动作类型
	public static final String TB_ACTION_TYPE = "action_type";
		//优先级
	public static final String TB_PRIORITY = "priority";
		//动作类型值
	public static final String TB_ACTION_TYPE_VAL = "action_type_val";
		//数据生效开始时间
	public static final String TB_START_TIME = "start_time";
		//数据生效结束时间
	public static final String TB_END_TIME = "end_time";
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

	//品牌代码
	public static final String TB_BRAND_CODE = "brand_code";

	@Override
    protected Serializable pkVal() {
        return null;
    }

	    //自增id

    private Long id;

	    //代理商ID

    private String xyPubCode;

	    //短信编码

    private String xySmsCode;

		//情景编号
    private String sceneCode;

	    //按钮名称

    private String btnName;

	    //动作类型

    private ActionTypeEnum actionType;

	    //优先级

    private Integer priority;

	    //动作类型值

    private String actionTypeVal;

	    //数据生效开始时间

    private Date startTime;

	    //数据生效结束时间

    private Date endTime;

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

	//品牌代码

	private String brandCode;


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
	 * 设置：代理商ID
	 */
	public void setXyPubCode(String xyPubCode) {
		this.xyPubCode = xyPubCode;
	}
	/**
	 * 获取：代理商ID
	 */
	public String getXyPubCode() {
		return xyPubCode;
	}
	/**
	 * 设置：按钮名称
	 */
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	/**
	 * 获取：按钮名称
	 */
	public String getBtnName() {
		return btnName;
	}
	/**
	 * 设置：动作类型
	 */
	public void setActionType(ActionTypeEnum actionType) {
		this.actionType = actionType;
	}
	/**
	 * 获取：动作类型
	 */
	public ActionTypeEnum getActionType() {
		return actionType;
	}
	/**
	 * 设置：优先级
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级
	 */
	public Integer getPriority() {
		return priority;
	}
	/**
	 * 设置：动作类型值
	 */
	public void setActionTypeVal(String actionTypeVal) {
		this.actionTypeVal = actionTypeVal;
	}
	/**
	 * 获取：动作类型值
	 */
	public String getActionTypeVal() {
		return actionTypeVal;
	}
	/**
	 * 设置：数据生效开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：数据生效开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：数据生效结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：数据生效结束时间
	 */
	public Date getEndTime() {
		return endTime;
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

	public String getSceneCode() {
		return sceneCode;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
    /**
     * 获取：短信编码
     */
    public String getXySmsCode() {
        return xySmsCode;
    }
    /**
     * 设置：短信编码
     */
    public void setXySmsCode(final String xySmsCode) {
        this.xySmsCode = xySmsCode;
    }

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
}
