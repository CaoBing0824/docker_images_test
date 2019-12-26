package com.xy.boot.open.model.params;

import com.xy.boot.open.model.annotation.EnumValidAnnotation;
import com.xy.boot.open.model.annotation.JsonAnnotation;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 基础参数
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2018-12-29.
 **/
@Data
public class BaseParam {

    /**
     * 扩展信息
     * checkEmpty可为空
     */
    @Length(max = 500, message = "扩展信息长度最大500")
    @JsonAnnotation(message = "扩展数据需为json字符串", checkEmpty = 0)
    private String extMap;
    /**
     * 操作类型
     */
    @NotBlank(message = "操作类型不能为空")
    @EnumValidAnnotation(message = "操作类型错误", target = OperateTypeEnum.class)
    private String type;

}
