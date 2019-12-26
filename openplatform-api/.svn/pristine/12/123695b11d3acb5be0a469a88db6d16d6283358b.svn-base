package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.AreasValidAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import com.xy.boot.open.util.StringUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * 菜单关联数据
 *
 * @author Ray
 * @Date: 2018-12-29
 */
@Data
public class RelationInfoParam extends BaseParam {

	/**
	 * 小源关联编码， (首次为空、更新不能为空)
	 */
	@XyCodeValidAnnotation(message = "关联编码不存在", dataType = DataTypeEnum.MENU_RELATION)
	private String xyRelationCode;

	/**
	 * 必填，小源公众号编码
	 */
	@NotBlank(message = "公众号编码不能为空")
	@XyCodeValidAnnotation(message = "公众号编码无效", dataType = DataTypeEnum.PUB)
	private String xyPubCode;

	/**
	 * 非必填，小源号码编码
	 */
	@XyCodeValidAnnotation(message = "号码编码无效", dataType = DataTypeEnum.CHANNEL)
	private String xyNumCode;

	/**
	 * 必填，小源菜单编码
	 */
	@NotBlank(message = "菜单编码不能为空")
	@XyCodeValidAnnotation(message = "菜单编码无效", dataType = DataTypeEnum.MENU)
	private String xyMenuCode;

	/**
	 * 非必填，用户标识Id，最大长度=50
	 */
	@Length(max = 50, message = "用户标识Id长度不能大于50")
	private String agPhoneId;

	/**
	 * 必填，区域1.5 区域省份编码对应表，多个英文分号隔开‘;’最大长度=500
	 */
	@Length(max = 500, message = "区域省份编码长度不能大于500")
	@AreasValidAnnotation(message = "区域数据错误", checkEmpty = 0)
	private String areas;

	/**
	 * 作逻辑使用，不作为入参
	 */
	private List<String> areasLs;

	/**
	 * 作逻辑使用，不作为入参
	 */
	private String version;

	/**
	 * 代理商编码  作逻辑使用，不作为入参
	 */
	private String xyAgentCode;

	public void setAreas(String areas) {
		this.areasLs = StringUtils.getList(areas);
		this.areas = areas;
	}
}
