package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.AgentSceneAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import com.xy.boot.open.util.StringUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 短信卡片上报
 *
 * @author Ray
 * @Date: 2018-12-29
 */
@Data
public class SmsParseInfoParam extends BaseParam {

	/**
	 * 必填，小源公众号编码
	 */
	@NotBlank(message = "公众号编码不可为空")
	@XyCodeValidAnnotation(message = "公众号编码无效",dataType = DataTypeEnum.PUB)
	private String xyPubCode;

	/**
	 * 小源卡片编码，(首次为空、更新不能为空)
	 */
	@XyCodeValidAnnotation(message = "卡片编码无效",dataType = DataTypeEnum.BUBBLE)
	private String xySmsCode;

	/**
	 * 必填，情景编码，最大长度=50，小源提供
	 */
	@NotBlank(message = "情景编码不可为空")
	@AgentSceneAnnotation(message = "情景编码不存在")
	private String sceneCode;

	/**
	 * 必填，短信内容 ，最大长度=1000
	 */
	@Length(max = 1000, message = "短信内容长度不能大于1000")
	@NotBlank(message = "短信内容不可为空")
	private String smsContent;

	/**
	 * 必填，短信模板，最大长度=1000
	 */
	@Length(max = 1000, message = "短信模板长度不能大于1000")
	@NotBlank(message = "短信模板不可为空")
	private String smsTemplate;

	/**
	 * 必填，期望字段提取，最大长度=1000  消费金额：300元;卡号：4611;消费时间：11日12:16;交易类型：ATM支持（取款）;手续费：4元 英文分号隔开
	 */
	@Length(max = 1000, message = "期望字段提取长度不能大于1000")
	@NotBlank(message = "期望字段提取不能为空")
	private String parseContent;

	/**作逻辑使用，不作为入参 */
	private String version;

	/**代理商编码 作逻辑使用，不作为入参*/
	private String xyAgentCode;

}
