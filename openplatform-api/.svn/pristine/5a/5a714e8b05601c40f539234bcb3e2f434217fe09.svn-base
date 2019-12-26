package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TDataRelationRelease;
import com.xy.boot.open.entity.TWhiteInfo;
import com.xy.boot.open.model.params.UpdateStatusParam;
import com.xy.boot.open.util.result.BasicRespMessage;


public interface IUpOffLineService extends IService<TDataRelationRelease> {
    //申请下线
    ReturnDTO offLine(UpdateStatusParam param);
    //申请上线
    ReturnDTO onLine(UpdateStatusParam param);
}
