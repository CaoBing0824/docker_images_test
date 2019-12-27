package com.xy.boot.open.model.bo;

import lombok.Data;

/**
 * 服务按钮区域统计数
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-02-25.
 **/
@Data
public class BtnAreaCountDetailsDataBO extends BtnCountDetailsDataBO{
    /**
     * 区域
     */
    private String area;
}
