package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TDataRelationHistory;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;

/**
 * 关联数据历史信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IDataRelationHistoryService extends IService<TDataRelationHistory> {
    /**
     * 查询信息
     *
     * @param xyCode
     * @param status
     * @param dataType
     * @return TDataRelationHistory
     */
    TDataRelationHistory getData(String xyCode, CheckStatusEnum status, DataTypeEnum dataType);

    /**
     * 检查xyCode是否有效,用于接口参数确定
     *
     * @param xyCode
     * @return
     */
    boolean checkXyCode(String xyCode,DataTypeEnum dataType);
}