package com.xy.boot.open.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @author heyuancheng@mfexcel.com
 *         Date:2019-02-25.
 **/
@Data
public class StatisticPubDataBO {
    /**
     * 公众号编码
     */
    private String xyPubCode;

    /**
     * 短信解析量
     */
    private String smsParseCount;

    /**
     * 菜单数据
     */
    private List<StatisticMenuDetailDataBO> menuDatas;

    /**
     * 按钮展现数
     */
    private List<BtnCountDetailsDataBO> btnShowCountDetail;
    /**
     * 按钮点击数
     */
    private List<BtnCountDetailsDataBO> btnClickCountDetail;
    /**
     * 按钮点击数
     */
    private List<BtnAreaCountDetailsDataBO> btnClickAreaCountDetail;
}
