package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.PatternAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PubInfoConfirmParam {
    /**
     * 公众号编码
     */
    @NotBlank(message = "公众号编码不能为空")
    //@XyCodeValidAnnotation(message = "公众号编码无效",dataType = DataTypeEnum.PUB)
    private String pubCode;

    /**
     * 业务结果
     */
    @NotBlank(message = "业务结果不能为空")
    @PatternAnnotation(regexp = "[01]", message = "业务结果值不合法,值为1或0")
    private String bizRet;//1 通过  0不通过

    /**
     * 结果描述
     */
    private String desc;
}
