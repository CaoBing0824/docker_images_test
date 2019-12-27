package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.ActionTypeEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 短信服务上报
 *
 * @author Ray
 * @Date: 2018-12-29
 */
@Data
public class ActionInfoParam extends BaseParam {

    /**
     * 必填，小源公众号编码
     */
    @NotBlank(message = "公众号编码 不可为空")
    @XyCodeValidAnnotation(message = "公众号编码无效", dataType = DataTypeEnum.PUB)
    private String xyPubCode;

    /**
     * 小源卡片编码 非必填
     */
    @Length(max = 50, message = "小源卡片编码长度最大50")
    @XyCodeValidAnnotation(message = "卡片编码无效", dataType = DataTypeEnum.BUBBLE)
    private String xySmsCode;

    /**
     * 必填，情景编码，最大长度=50
     */
    @NotBlank(message = "情景编码不可为空")
    @AgentSceneAnnotation(message = "情景编码不存在")
    private String sceneCode;

    /**
     * 小源服务按钮编码， (首次为空、更新不能为空)
     */
    @XyCodeValidAnnotation(message = "服务按钮编码无效", dataType = DataTypeEnum.BUBBLE_BTN)
    private String xyBtnCode;

    /**
     * 必填，按钮名称，最大长度=10
     */
    @Length(max = 20, message = "按钮名称长度不能大于20")
    @NotBlank(message = "按钮名称不能为空")
    private String btnName;

    /**
     * 必填，动作类型  *发送短信：SEND_SMS *打开URL：OPEN_URL *拨打电话：CALL *打开APP：OPEN_APP
     */
    @NotBlank(message = "动作类型不可为空")
    @EnumValidAnnotation(message = "动作类型错误", target = ActionTypeEnum.class)
    private String actionType;

    /**
     * 动作类型值详见1.2.3.动作类型说明
     */
    @Length(max = 2048, message = "返回提示：总长度不能超过2048")
    @NotBlank(message = "动作类型值不可为空")
    @JsonAnnotation(message = "动作类型值需为json字符串", checkEmpty = 1)
    private String actionTypeVal;

    /**
     * 必填，优先级
     */
    @NotBlank(message = "优先级不可为空")
    @IntegerAnnotation(message = "优先级需为数值型")
    private String priority;

    /**
     * 非必填，数据生效开始时间(UTC时间 如2017-03-23T06:59:55Z)
     */
    @PatternAnnotation(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z", message = "开始时间格式错误,须为UTC时间格式")
//    @DateTimeAfterNowAnnotation(message = "失效时间>生效时间>当前时间")
    private String startTime;

    /**
     * 非必填，数据生效结束时间(UTC时间 如2017-03-23T06:59:55Z)
     */
    @PatternAnnotation(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z", message = "结束时间格式错误,须为UTC时间格式")
//    @DateTimeAfterNowAnnotation(message = "失效时间>生效时间>当前时间")
    private String endTime;

    private String xyAgentCode;

    private String version;

    /**
     * 品牌代码 非必填
     */
    private String brandCode;
}