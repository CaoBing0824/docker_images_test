package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.TOpenIndustryInfo;
import com.xy.boot.open.entity.enums.IndustryInfoStatusEnum;
import com.xy.boot.open.mapper.TOpenIndustryInfoMapper;
import com.xy.boot.open.service.IOpenIndustryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2006-2019, xy
 * FileName: OpenIndustryInfoServiceImpl
 * 行业信息
 *
 * @author maisenlin@mfexcel.com
 * @version 1.0.0
 * @Date 2019-06-25 14:26
 **/
@Slf4j
@Service
public class OpenIndustryInfoServiceImpl extends ServiceImpl<TOpenIndustryInfoMapper, TOpenIndustryInfo> implements IOpenIndustryInfoService {

    @Override
    public boolean checkIndustryCode(String industryCode, IndustryInfoStatusEnum status) {
        if( StringUtils.isEmpty(industryCode) ) return false;
        if( null == status) return false;

        Wrapper<TOpenIndustryInfo> wrapper = new EntityWrapper<>();
        wrapper.eq(TOpenIndustryInfo.TB_INDUSTRY_CODE, industryCode);
        TOpenIndustryInfo one = this.selectOne(wrapper);
        if( one != null
                && status.getStatusCode().equals(one.getPermissionYellowReport())){
            return true;
        }
        return false;
    }

    @Override
    public boolean existIndustryCode(String industryCode) {
        if( StringUtils.isEmpty(industryCode) ) return false;

        Wrapper<TOpenIndustryInfo> wrapper = new EntityWrapper<>();
        wrapper.eq(TOpenIndustryInfo.TB_INDUSTRY_CODE, industryCode);
        TOpenIndustryInfo one = this.selectOne(wrapper);
        if(one != null
                && one.getIndustryCode().equals(industryCode)){
            return true;
        }
        return false;
    }
}
