package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TOpenLogoInfo;

/**
 * 公众号logo信息
 *
 * @author  maisenlin@mfexcel.com
 * @since 2019-06-10
 */
public interface IOpenLogoInfoService extends IService<TOpenLogoInfo> {

    TOpenLogoInfo getOneByXyPubCode(String xyPubCode);
}
