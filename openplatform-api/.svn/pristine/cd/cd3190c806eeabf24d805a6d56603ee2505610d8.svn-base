package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TDataRelationEdit;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;

/**
 * 数据关联信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IDataRelationEditService extends IService<TDataRelationEdit> {

    /**
     * 根据 xyCode 获取数据
     *
     * @param xyCode
     * @return TDataRelationEdit
     */
    TDataRelationEdit getByXyCode(String xyCode);

    /**
     * 更新关联信息
     *
     * @param xyCode
     * @param version
     * @param dataType
     * @param checkStatus
     */
    void updateDataRelation(String xyCode, String version, DataTypeEnum dataType, CheckStatusEnum checkStatus);

}