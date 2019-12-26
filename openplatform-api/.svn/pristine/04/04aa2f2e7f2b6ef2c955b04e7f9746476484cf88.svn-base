package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TBPublicReceiveNumRelease;
import com.xy.boot.open.mapper.TBPublicReceiveNumReleaseMapper;
import com.xy.boot.open.service.PublicReceiveNumReleaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 户部通道号
 *
 * @author maisenlin@mfexcel.com
 * @since 2019-06-03 12:09
 **/

@Slf4j
@Service
public class PublicReceiveNumReleaseServiceImpl extends ServiceImpl<TBPublicReceiveNumReleaseMapper, TBPublicReceiveNumRelease> implements PublicReceiveNumReleaseService {

    @Autowired
    private TBPublicReceiveNumReleaseMapper tBPublicReceiveNumReleaseMapper;

    @Override
    public String getOneByCondition(String pubReceiveNum, String area, int statu) {
        String result = tBPublicReceiveNumReleaseMapper.getPublicNumByCondition(pubReceiveNum, area, statu);
        if( StringUtils.isNotEmpty(result) ){
            return result;
        }
        return null;
    }
}
