package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.MuiltDataValidAnnotation;
import com.xy.boot.open.model.annotation.MuiltDataValidSizeAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import com.xy.boot.open.util.StringUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.*;


/**
 * 上报白名单设备接口参数Vo
 *
 * @Author: Ray
 * @Date: 2018-12-29
 */
@Data
public class WhiteInfoParam extends BaseParam{

	/**小源白名单信息编码， (首次为空、更新不能为空)*/
	@XyCodeValidAnnotation(message = "白名单信息编码无效",dataType = DataTypeEnum.WHITE_LIST)
	private String xyWhiteCode;

	/**小源公众号编码，多个英文分号‘;’间隔,最大长度=1000*/
	@Length(max = 3000, message = "公众号编码长度非法")
	@NotBlank(message = "公众号编码不能为空")
	private String xyPubCodes;

	/**必填，手机品牌，最大长度=20*/
	@Length(max = 20, message = "手机品牌长度不能大于20")
	@NotBlank(message = "手机品牌不能为空")
	private String phoneBrand;

	/**必填，手机型号，最大长度=20*/
	@Length(max = 20, message = "手机型号长度不能大于20")
	@NotBlank(message = "手机型号不能为空")
	private String phoneType;

	/**必填，IMEI/SN等，多个英文分号 ; 隔开，最大长度=83*/
	@Length(max = 83, message = "设备码长度不能大于83")
	@NotBlank(message = "设备码不能为空")
	@MuiltDataValidSizeAnnotation(message = "设备码最大4个", listsize = 4)
	@MuiltDataValidAnnotation(message = "单个设备码最大20个", strlength = 20)
	private String imeis;

	/**作逻辑使用，不作为入参 */
	private List<String> imeiLs;

	/**作逻辑使用，不作为入参 */
	private List<String> xyPubCodeLs;

	/**代理商编码 作逻辑使用，不作为入参*/
	private String xyAgentCode;

	/** 小源版本号 作逻辑使用，不作为入参 */
	private String version;

	public void setImeis(String imeis) {
		this.imeiLs = StringUtils.getList(imeis);
		this.imeis = imeis;
	}

	public void setXyPubCodes(String xyPubCodes) {
		this.xyPubCodeLs = StringUtils.getList(xyPubCodes);
		this.xyPubCodes = xyPubCodes;
	}
}
