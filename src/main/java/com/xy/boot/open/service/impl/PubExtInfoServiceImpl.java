package com.xy.boot.open.service.impl;

import com.xy.boot.open.mapper.TOpenApiPubExtInfoMapper;
import com.xy.boot.open.service.IPubExtInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PubExtInfoServiceImpl implements IPubExtInfoService {

    @Autowired
    private TOpenApiPubExtInfoMapper tOpenApiPubExtInfoMapper;

    @Override
    public String getPubIdByPubCode(String pubCode) {
        return tOpenApiPubExtInfoMapper.getPubIdByPubCode(pubCode);
    }

    @Override
    public String getAcceptanceStatusByPubCode(String pubCode) {
        return tOpenApiPubExtInfoMapper.getAcceptanceStatusByPubCode(pubCode);
    }

    @Override
    public Date getExpiryDateByPubCode(String pubCode) {
        return tOpenApiPubExtInfoMapper.getExpiryDateByPubCode(pubCode);
    }

}
