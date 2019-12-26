package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TBrandInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBrandInfoService  extends IService<TBrandInfo> {
    /**
     * 获取品牌名称
     * @param brandCodes
     * @return
     */
    List<String> getBrandCodeByCode(@Param("brandCode") List<String> brandCodes);

    String isBrandCodeValid(String brandCodes,int count ,int len);

    List<String> checkBrandExists(String brandCode);
}
