package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TOpenLogoInfo;
import com.xy.boot.open.mapper.TOpenApiLogoInfoMapper;
import com.xy.boot.open.service.IOpenLogoInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 公众号logo信息
 *
 * @author maisenlin@mfexcel.com
 * @since 2019-06-10 19:35
 **/
@Slf4j
@Service
public class IOpenLogoInfoServiceImpl extends ServiceImpl<TOpenApiLogoInfoMapper, TOpenLogoInfo> implements IOpenLogoInfoService {

    @Override
    public TOpenLogoInfo getOneByXyPubCode(final String xyPubCode) {
        Wrapper<TOpenLogoInfo> wrapper = new EntityWrapper<>() ;
        wrapper.eq(TOpenLogoInfo.TB_XY_PUB_CODE, xyPubCode);
        wrapper.orderBy(TOpenLogoInfo.TB_CREATED, false);
        wrapper.last("limit 1");
        return this.selectOne(wrapper);
    }
}
