package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.ProductTypeEnum;
import com.xy.boot.open.model.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 公众号信息接口参数
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-02 下午 03:36
 */
@Data
public class PubInfoParam extends BaseParam {
    /**
     * 企业编码
     */
    @Length(max = 50, message = "企业编码不能超过50个字符")
    @NotBlank(message = "企业编码不能为空")
    @XyCodeValidAnnotation(message = "企业编码无效", dataType = DataTypeEnum.ENT)
    private String xyEntCode;
    /**
     * 公众号编码
     */
    @Length(max = 50, message = "公众号编码不能超过50个字符")
    @XyCodeValidAnnotation(message = "公众号编码无效", dataType = DataTypeEnum.PUB)
    private String xyPubCode;
    /**
     * 公众号名称
     */
    @Length(max = 40, message = "公众号名称不能超过40个字符")
    @NotBlank(message = "公众号名称不能为空")
    private String pubName;
    /**
     * 白底图片资源
     */
    @URL(message = "白底图片资源不合法")
    @Length(max = 2048, message = "白底图片资源长度最大2048")
    @SuffixValidAnnotation(message = "白底图片资源不合法,需为png,jpg", suffix = "png,jpg")
    private String showLogoWhite;
    /**
     * 彩色图片资源
     */
    @URL(message = "彩色图片资源不合法")
    @Length(max = 2048, message = "彩色图片资源长度最大2048")
    @SuffixValidAnnotation(message = "彩色图片资源不合法,需为png,jpg", suffix = "png,jpg")
    private String showLogoColor;
    /**
     * 产品类型
     */
    @EnumValidAnnotation(message = "产品类型错误", target = ProductTypeEnum.class)
    @NotBlank(message = "产品类型不能为空")
    private String productType;

    /**
     * 行业编码
     */
    @IndustryCodeValidAnnotation
    private String industryCode;

    /**
     * 合作月
     */
    @IntegerAnnotation(message = "合作月需为数值型")
    @Pattern(regexp = "^\\s*$|^(?:1[0-2]|[1-9])$", message = "合作月数在1~12个月之间")
    private String cooperativeMonth;
}
