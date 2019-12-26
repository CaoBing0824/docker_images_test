package com.xy.boot.open.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xy.boot.open.entity.TBrandInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TOpenApiBrandInfoMapper extends BaseMapper<TBrandInfo> {
    /**
     * 获取品牌名称
     * @param brandCodes
     * @return
     */
    List<String> getBrandCodeByCode(@Param("brandCodes") List<String> brandCodes);
}
