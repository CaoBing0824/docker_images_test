package com.xy.boot.open.mapper;

import com.xy.boot.common.base.dao.BaseDao;
import com.xy.boot.open.entity.TDataRelationEdit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-02 上午 09:53
 */
@Mapper
public interface TOpenApiDataRelationEditMapper extends BaseDao<TDataRelationEdit> {

    /**
     * 更新关联信息
     *
     * @param xyCode
     * @param version
     * @param dataType
     * @param workorderId
     * @param checkStatus
     * @param status
     */
    void updateDataRelation(@Param("xyCode") String xyCode, @Param("version") String version, @Param("dataType") String dataType, @Param("workorderId") long workorderId, @Param("checkStatus") Integer checkStatus, @Param("status") Integer status);

}