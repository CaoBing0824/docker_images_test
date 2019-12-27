package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TEntInfo;
import com.xy.boot.open.model.params.EntInfoParam;

/**
 * 企业信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IEntInfoService extends IService<TEntInfo> {

    /**
     * 企业信息上报
     *
     * @param param
     * @return
     */
    ReturnDTO upload(EntInfoParam param);


}