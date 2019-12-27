package com.xy.boot.open.model.bo;

import lombok.Data;

import java.util.List;

/**
 * 服务按钮统计数
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-02-25.
 **/
@Data
public class BtnCountDetailsDataBO {
    /**
     * 情景编码
     */
    private String sceneCode;
    /**
     * 按钮名称
     */
    private String buttonName;
    /**
     * 数量
     */
    private String count;
}
