package com.xy.boot.open.model.params;

import com.xy.boot.open.model.annotation.MuiltDataValidAnnotation;
import com.xy.boot.open.model.annotation.MuiltDataValidSizeAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PubInfoRenewalQueryParam {

    /**
     * 公众号编码
     */
    @MuiltDataValidSizeAnnotation(message = "公众号编码最多100个", listsize = 100)
    @MuiltDataValidAnnotation(message = "单个公众号编码字符最长为50,且不为空", strlength = 50)
    @NotBlank(message = "公众号编码不能为空")
    private String xyPubCode;

}
