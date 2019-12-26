package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TMenuInfo;
import com.xy.boot.open.model.params.MenuInfoParam;

/**
 * 菜单信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IMenuInfoService extends IService<TMenuInfo> {
    /**
     * 菜单信息上报
     *
     * @param param
     * @return
     */
    ReturnDTO upload(MenuInfoParam param);

    String getMenuCodeByPubCode(String pubCode);
}