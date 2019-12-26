package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TSmsInfo;
import com.xy.boot.open.model.params.SmsParseInfoParam;

import java.util.List;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface ISmsInfoService extends IService<TSmsInfo> {

    /**
     * 上传卡片数据
     *
     * @param param
     * @return
     */
    ReturnDTO upload(SmsParseInfoParam param);

    /**
     * 根据公众号编码和短信编码获取列表
     *
     * @param xyPubCode
     * @param xyCode
     * @return
     */
    List<TSmsInfo> getListByCode(String xyPubCode, String xyCode);
}