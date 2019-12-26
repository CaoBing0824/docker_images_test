package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TBPublicReceiveNumRelease;

/**
 * 户部通道号
 */
public interface PublicReceiveNumReleaseService extends IService<TBPublicReceiveNumRelease> {

    /**
     *  查询户部通道号
     * @param pubReceiveNum 通道号
     * @param area 区域
     * @param statu 启用状态：户部状态1.启用 2.验证通过 0.户部下线,脚本上线 3.验证中 -1.下线 -2未验证
     * @return
     */
    String  getOneByCondition( String pubReceiveNum, String area, int statu);

}
