package com.xy.boot.open.mapper;

import com.xy.boot.common.base.dao.BaseDao;
import com.xy.boot.open.entity.TDataRelationRelease;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TOpenApiDataRelationReleaseMapper extends BaseDao<TDataRelationRelease> {
    /**
     * 更新关联信息
     *
     * @param xyCode
     * @param version
     * @param dataType
     * @param workorderId
     */
    void updateDataRelation(@Param("xyCode") String xyCode, @Param("version") String version, @Param("dataType") String dataType, @Param("workorderId") long workorderId, @Param("checkStatus") Integer checkStatus, @Param("status") Integer status);
}