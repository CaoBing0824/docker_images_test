package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TDataRelationHistory;
import com.xy.boot.open.model.params.CheckInfoQueryParam;

public interface ICheckInfoQueryService extends IService<TDataRelationHistory> {
    //查询审核状态
    ReturnDTO getCheckInfoQueryStatus(CheckInfoQueryParam param);
}
