package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TPubInfo;
import com.xy.boot.open.model.params.*;

import java.util.Date;
import java.util.List;

/**
 * 公众号信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IPubInfoService extends IService<TPubInfo> {
    /**
     * 公众号信息上报
     *
     * @param param
     * @return
     */
    ReturnDTO upload(PubInfoParam param);

    /**
     * 根据公众号编码查找
     *
     * @param pubCode
     * @return
     */
    List<TPubInfo> getInfo(String pubCode);

    /**
     * 根据公众号编码查找
     * @param pubCode
     * @return
     */
    TPubInfo getOne(String pubCode);

    /**
     * 公众号续约
     * @param param
     * @return
     */
    ReturnDTO renewContract(PubInfoRenewalContractParam param , Date date );

    /**
     * 公众号续约通过通知
     * @param pubCodes
     * @return
     */
    ReturnDTO renewContractNotice(String[] pubCodes);

    /**
     * 公众号验收确认
     * @param param
     * @return
     */
    ReturnDTO initiateAcceptanceConfirm(PubInfoAcceptanceConfirmParam param);

    /**
     * 构造公众号验收确认结果子集
     * @param pubCode
     * @return
     */
    PubInfoAcceptanceResultParam structuredAcceptanceResultObj(String pubCode);

}