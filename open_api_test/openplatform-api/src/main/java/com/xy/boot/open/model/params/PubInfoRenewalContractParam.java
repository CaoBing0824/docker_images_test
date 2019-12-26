package com.xy.boot.open.model.params;

import com.xy.boot.open.model.annotation.PatternAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PubInfoRenewalContractParam {
    /**
     * 公众号编码
     */
    @NotBlank(message = "公众号编码不能为空")
    @Length(max = 50, message = "公众号编码不能超过50个字符")
    private String xyPubCode;

    /**
     * 新合作到期日期（必须大于当前日期）
     */
    @NotBlank(message = "新合作到期日期不能为空")
    @PatternAnnotation(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "日期格式不正确，yyyy-MM-dd")
    private String newExpiryDate;

}
