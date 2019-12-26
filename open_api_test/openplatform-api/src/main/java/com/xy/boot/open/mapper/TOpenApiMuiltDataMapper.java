package com.xy.boot.open.mapper;

import com.xy.boot.common.base.dao.BaseDao;
import com.xy.boot.open.entity.TMuiltData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TOpenApiMuiltDataMapper extends BaseDao<TMuiltData> {

	void insertBatch(@Param("dataList") List<TMuiltData> data);

}