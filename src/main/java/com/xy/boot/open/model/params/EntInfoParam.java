package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.SuffixValidAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 企业信息接口参数
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-28 下午 02:33
 */
@Data
public class EntInfoParam extends BaseParam {
    /**
     * 企业编码
     */
    @Length(max = 50, message = "企业编码长度不能超过50个字符")
    @XyCodeValidAnnotation(message = "企业编码无效", dataType = DataTypeEnum.ENT)
    private String xyEntCode;
    /**
     * 企业名称
     */
    @NotBlank(message = "企业名称不能为空")
    @Length(max = 100, message = "企业名称不能超过100个字符")
    private String entName;
    /**
     * 企业统一信用注册码 18个字符，必须有数字或字母组成
     */
    //@NotBlank(message = "企业统一信用注册码不能为空")
    @Length(min = 18, max = 18, message = "企业统一信用注册码须为18位数字或字母")
    @Pattern(regexp = "[0-9A-Za-z]+", message = "企业统一信用注册码只能由数字或字母组成")
    private String creditCode;
    /**
     * 企业授权书
     */
    @URL(message = "企业授权书不合法")
    @Length(max = 200, message = "企业授权书不能超过200个字符")
    @SuffixValidAnnotation(message = "企业授权书不合法,文件名需为png,jpg,pdf", suffix = "png,jpg,pdf")
    private String authDocx;

}
