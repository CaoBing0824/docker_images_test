package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TNumInfo;
import com.xy.boot.open.model.params.ChannelInfoParam;

/**
 * 号码信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface INumInfoService extends IService<TNumInfo> {
    /**
     * 通道号信息上报
     *
     * @param param
     * @return
     */
    ReturnDTO upload(ChannelInfoParam param);
}