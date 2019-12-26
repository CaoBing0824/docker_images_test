package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;


@Data
public class CheckInfoQueryParam {

    @Length(max = 50, message = "数据编码不能超过50个字符")
    @NotBlank(message = "数据编码不能为空")
    @XyCodeValidAnnotation(message = "数据编码不存在")
    private String xyCode;
}
