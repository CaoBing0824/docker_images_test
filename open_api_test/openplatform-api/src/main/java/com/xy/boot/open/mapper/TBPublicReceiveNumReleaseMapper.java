package com.xy.boot.open.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xy.boot.common.base.dao.BaseDao;
import com.xy.boot.open.entity.TBPublicReceiveNumRelease;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
@DS("publicnum")
public interface TBPublicReceiveNumReleaseMapper extends BaseDao<TBPublicReceiveNumRelease> {

    String getPublicNumByCondition(@Param("pubReceiveNum") String pubReceiveNum, @Param("area") String area, @Param("statu") int statu);

}