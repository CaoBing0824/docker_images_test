package com.xy.boot.open.service;

import java.util.Date;

public interface IPubExtInfoService {

    String getPubIdByPubCode(String pubCode);

    /**
     * 获取验收状态
     * @param pubCode
     * @return
     */
    String getAcceptanceStatusByPubCode(String pubCode);


    /**
     * 查询公众号失效时间
     * @param pubCode
     * @return
     */
    Date getExpiryDateByPubCode(String pubCode);
}
