package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.ActionTypeEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 菜单信息接口参数
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-02 下午 03:36
 */
@Data
public class MenuInfoParam extends BaseParam {
    /**
     * 公众号编码
     */
    @Length(max = 50, message = "公众号编码不能超过50个字符")
    @NotBlank(message = "公众号编码不能为空")
    @XyCodeValidAnnotation(message = "公众号编码无效", dataType = DataTypeEnum.PUB)
    private String xyPubCode;
    @Length(max = 50, message = "菜单编码不能超过50个字符")
    @XyCodeValidAnnotation(message = "菜单编码无效", dataType = DataTypeEnum.MENU)
    private String xyMenuCode;
    /**
     * 非必填，数据生效时间(UTC时间 如2017-03-23T06:59:55Z)
     */
//    @DateTimeAfterNowAnnotation(message = "数据生效时间需大于当前时间")
    @PatternAnnotation(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z", message = "开始时间格式错误,须为UTC时间格式")
    private String startTime;

    /**
     * 非必填，数据失效时间(UTC时间 如2017-03-23T06:59:55Z)
     */
//    @DateTimeAfterNowAnnotation(message = "数据失效时间需大于当前时间")
    @PatternAnnotation(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z", message = "结束时间格式错误,须为UTC时间格式")
    private String endTime;
    /**
     * 左菜单名称
     */
    @Length(max = 20, message = "左菜单名称不能超过20个字符")
    @NotBlank(message = "左菜单名称不能为空")
    private String itemName01;
    /**
     * 左菜单类型
     */
    @EnumValidAnnotation(message = "左菜单类型错误", target = ActionTypeEnum.class)
    @NotBlank(message = "左菜单类型不能为空")
    private String itemType01;
    /**
     * 左菜单配置
     */
    @JsonAnnotation(message = "左菜单配置内容须为有效json字符", checkEmpty = 0)
    @Length(max = 2048, message = "左菜单配置内容不能超过2048个字符")
    private String itemTypeVal01;
    /**
     * 左子菜单集合
     */
    @Valid
    private List<SubMenuInfoParam> subMenu01;
    /**
     * 中菜单名称
     */
    @Length(max = 20, message = "中菜单名称不能超过20个字符")
    @NotBlank(message = "中菜单名称不能为空")
    private String itemName02;
    /**
     * 中菜单类型
     */
    @EnumValidAnnotation(message = "中菜单类型错误", target = ActionTypeEnum.class)
    @NotBlank(message = "中菜单类型不能为空")
    private String itemType02;
    /**
     * 中菜单配置
     */
    @JsonAnnotation(message = "中菜单配置内容须为有效json字符", checkEmpty = 0)
    @Length(max = 2048, message = "中菜单配置内容不能超过2048个字符")
    private String itemTypeVal02;
    /**
     * 中子菜单集合
     */
    @Valid
    private List<SubMenuInfoParam> subMenu02;
    /**
     * 右菜单名称
     */
    @Length(max = 20, message = "右菜单名称不能超过20个字符")
    @NotBlank(message = "右菜单名称不能为空")
    private String itemName03;
    /**
     * 右菜单类型
     */
    @EnumValidAnnotation(message = "右菜单类型错误", target = ActionTypeEnum.class)
    @NotBlank(message = "右菜单类型不能为空")
    private String itemType03;
    /**
     * 右菜单配置
     */
    @JsonAnnotation(message = "右菜单配置内容须为有效json字符", checkEmpty = 0)
    @Length(max = 2048, message = "右菜单配置内容不能超过2048个字符")
    private String itemTypeVal03;
    /**
     * 右子菜单集合
     */
    @Valid
    private List<SubMenuInfoParam> subMenu03;

    /**
     * 菜单描述 非必填
     */
    @Length(max = 50, message = "菜单描述长度最大50")
    private String menuDesc;

    /**
     * 品牌代码 非必填
     */
    private String brandCode;

}
