package com.xy.boot.open.model.params;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;


@Data
public class UpdateStatusParam {

    @Length(max = 50, message = "数据编码不能超过50个字符")
    @NotBlank(message = "数据编码不能为空")
    private String xyCode;
}
