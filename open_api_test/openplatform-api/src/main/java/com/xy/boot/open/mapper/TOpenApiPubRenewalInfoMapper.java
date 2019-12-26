package com.xy.boot.open.mapper;

import com.xy.boot.common.base.dao.BaseDao;
import com.xy.boot.open.entity.TOpenRenewalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface TOpenApiPubRenewalInfoMapper  extends BaseDao<TOpenRenewalInfo> {
    /**
     * 更新公众号续约记录
     */
    void updateRenewalByPubCode(@Param("status") int status, @Param("id") String id);

    /**
     * 根据公众号业务号业务状态查询续约记录
     */
    String getRenewalByPStatusAndBizCode(@Param("pubCode") String pubCode, @Param("bizCode") String bizCode,@Param("bizRet") String bizRet);

    /**
     * 根据公众号业务号业务状态查询续约记录
     */
    TOpenRenewalInfo getRenewalByPubCode(@Param("pubCode") String pubCode);

}
