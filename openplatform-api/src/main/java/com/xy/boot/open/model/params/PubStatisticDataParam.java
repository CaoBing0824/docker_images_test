package com.xy.boot.open.model.params;

import com.xy.boot.open.model.annotation.MuiltDataNotEmptyValidAnnotation;
import com.xy.boot.open.model.annotation.MuiltDataValidAnnotation;
import com.xy.boot.open.model.annotation.MuiltDataValidSizeAnnotation;
import com.xy.boot.open.model.annotation.PatternAnnotation;
import lombok.Data;

/**
 * @author heyuancheng@mfexcel.com
 *         Date:2019-02-25.
 **/
@Data
public class PubStatisticDataParam {
    /**
     * 公众号编码，多个，最大10个，英文分号间隔
     */
    @MuiltDataNotEmptyValidAnnotation(message = "公众号编码不能为空")
    @MuiltDataValidSizeAnnotation(message = "公众号编码最大10个", listsize = 10)
    @MuiltDataValidAnnotation(message = "单个公众号编码字符最大20个", strlength = 40)
    String xyPubCodes;
    /**
     * 时间 2019-01-01
     */
    @MuiltDataNotEmptyValidAnnotation(message = "日期不能为空")
    @PatternAnnotation(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "日期格式不正确，yyyy-MM-dd")
    String date;
}
