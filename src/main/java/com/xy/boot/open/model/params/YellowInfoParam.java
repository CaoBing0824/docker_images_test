package com.xy.boot.open.model.params;

import com.xy.boot.open.constant.RegexConstant;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.LonlatValidAnnotation;
import com.xy.boot.open.model.annotation.StringLengthAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 公众号信息接口参数
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-02 下午 03:36
 */
@Data
public class YellowInfoParam extends BaseParam {
    /**
     * 公众号编码
     */
    @Length(max = 50, message = "公众号编码不能超过50个字符")
    @NotBlank(message = "公众号编码不能为空")
    @XyCodeValidAnnotation(message = "公众号编码无效", dataType = DataTypeEnum.PUB)
    private String xyPubCode;
    /**
     * 通道号编码
     */
    @Length(max = 50, message = "黄页编码不能超过50个字符")
    @XyCodeValidAnnotation(message = "黄页编码无效", dataType = DataTypeEnum.YELLOW_PAGE)
    private String xyYellowCode;
    /**
     * 企业口号
     */
    @NotBlank(message = "企业口号不能为空")
    @StringLengthAnnotation(maxLen = 40, message = "企业口号不能超过40个字符，一个汉字占2个字符")
    //@Length(max = 100, message = "企业口号不能超过100个字符")
    private String slogan;
    /**
     * 企业介绍不能为空
     */
    @NotBlank(message = "企业介绍不能为空")
    @StringLengthAnnotation(maxLen = 280, message = "企业介绍不能超过280个字符，一个汉字占2个字符")
    //@Length(max = 1000, message = "企业介绍不能超过1000个字符")
    private String introduction;
    /**
     * 服务热线
     */
    @NotBlank(message = "服务热线不能为空")
    //@Length(max = 20, message = "服务热线不能超过20个字符")
    //@Pattern(regexp = RegexConstant.REGEX_LANDLINE, message = "服务热线必须为座机，例如：0760-88855579")
    private String tellPhone;
    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    @Length(max = 100, message = "地址不能超过100个字符")
    private String address;
    /**
     * 地址搜索关键词
     */
    @NotBlank(message = "地址搜索关键词不能为空")
    @Length(max = 100, message = "地址搜索关键词不能超过100个字符")
    private String serchKey;
    /**
     * 营业时间
     */
    @NotBlank(message = "营业时间不能为空")
    @StringLengthAnnotation(maxLen = 60, message = "营业时间不能超过60个字符，一个汉字占2个字符")
    //@Length(max = 100, message = "营业时间不能超过100个字符")
    private String openTime;
    /**
     * 经营范围
     */
    @NotBlank(message = "经营范围不能为空")
    @StringLengthAnnotation(maxLen = 100, message = "经营范围不能超过100个字符，一个汉字占2个字符")
    //@Length(max = 1000, message = "经营范围不能超过1000个字符")
    private String range;
    /**
     * 经纬度
     */
    //@NotBlank(message = "经纬度不能为空")
    @Length(max = 40,message = "经纬度不能超过40个字符")
    @LonlatValidAnnotation(message = "经纬度格式不正确，例：+116.494531,-39.958308")
    private String lonlat;
}
