package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 公众号信息接口参数
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-02 下午 03:36
 */
@Data
public class ChannelInfoParam extends BaseParam {
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
    @Length(max = 50, message = "通道号编码不能超过50个字符")
    @XyCodeValidAnnotation(message = "通道号编码无效", dataType = DataTypeEnum.CHANNEL)
    private String xyChannelCode;
    /**
     * 通道号
     */
    @Length(max = 50, message = "通道号不能超过50个字符")
    @NotBlank(message = "通道号不能为空")
    //@NumRangeAnnotation(message = "通道号表示错误,数字或数字+星号或者号段1069***~1069****")
    @NumRangeLengthAnnotation(message = "通道号单个号段最大20", maxlength = 20)
    private String receiveNum;
    /**
     * 区域
     */
    @Length(max = 200, message = "区域不能超过200个字符")
    @NotBlank(message = "区域不能为空")
    @MuiltDataNotEmptyValidAnnotation(message = "区域不能为空")
    @AreasValidAnnotation(message = "区域数据错误")
    private String areas;
    /**
     * 签名
     */
    @Length(max = 500, message = "签名不能超过500个字符")
    @NotBlank(message = "签名不能为空")
    @MuiltDataNotEmptyValidAnnotation(message = "签名不能为空")
    @MuiltDataValidSizeAnnotation(message = "签名数据最大10个", listsize = 10)
    @MuiltDataValidAnnotation(message = "单个签名字符最大40个", strlength = 40)
    private String signatures;
    /**
     * 用途
     */
    @Length(max = 40, message = "用途不能超过40个字符")
    private String purpose;

    /**
     * 是否公众号专属号码
     */
    @PatternAnnotation(regexp = "[0-1]{1}", message = "是否公众号专属号码,值不合法 ")
    private String numExclusive;
}
