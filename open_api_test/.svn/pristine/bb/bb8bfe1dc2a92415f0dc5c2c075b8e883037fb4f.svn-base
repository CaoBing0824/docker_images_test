package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TDataRelationEdit;
import com.xy.boot.open.entity.TDataRelationHistory;
import com.xy.boot.open.entity.TDataRelationRelease;
import com.xy.boot.open.entity.TOpenCheckData;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.TOpenApiDataRelationReleaseMapper;
import com.xy.boot.open.model.params.UpdateStatusParam;
import com.xy.boot.open.service.ICheckDataService;
import com.xy.boot.open.service.IDataRelationEditService;
import com.xy.boot.open.service.IDataRelationHistoryService;
import com.xy.boot.open.service.IUpOffLineService;
import com.xy.boot.open.util.result.RespSimpleBody;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UpOffLineServiceImpl extends ServiceImpl<TOpenApiDataRelationReleaseMapper, TDataRelationRelease> implements IUpOffLineService {

    @Autowired
    private IDataRelationHistoryService relationHistoryService;

    @Autowired
    private ICheckDataService checkDataService;

    @Autowired
    private IDataRelationEditService editService;

    @Override
    public ReturnDTO offLine(UpdateStatusParam param) {
        DateTime now = new DateTime();
        Date date = now.toDate();
        //查询xyCode在历史表中，是否存在
        EntityWrapper<TDataRelationHistory> relationReleaseHistory = new EntityWrapper<>();
        relationReleaseHistory.eq(TDataRelationRelease.TB_XY_CODE, param.getXyCode())
                .eq(TDataRelationRelease.TB_STATUS, StatusEnum.VALID);

        TDataRelationHistory tDataRelationReleaseHistory = relationHistoryService.selectOne(relationReleaseHistory);

        if(tDataRelationReleaseHistory == null){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "数据编码不存在");
        }

        DataTypeEnum dataType = tDataRelationReleaseHistory.getDataType();

        //企业信息,短信卡片或短信白名单没有申请下线操作
        if(dataType == DataTypeEnum.ENT || dataType == DataTypeEnum.BUBBLE || dataType == DataTypeEnum.WHITE_LIST){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "数据编码不存在");
        }

        EntityWrapper<TDataRelationRelease> relationRelease = new EntityWrapper<>();
        relationRelease.eq(TDataRelationRelease.TB_XY_CODE, param.getXyCode())
                .eq(TDataRelationRelease.TB_STATUS, StatusEnum.VALID);

        TDataRelationRelease tDataRelationRelease = this.selectOne(relationRelease);
        //xyCode数据在历史表中存在，而在正式表中不存在 或 在正式表中状态不为“已上线”。
        if (tDataRelationRelease == null || tDataRelationRelease.getCheckStatus() != CheckStatusEnum.DATA_STATUS_RELEASE) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "仅支持已上线数据进行下线操作");
        }
        TOpenCheckData checkData = new TOpenCheckData();
        checkData.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_OFFLINE.getStatusCode());
        checkData.setXyCode(tDataRelationRelease.getXyCode());
        checkData.setVersion(tDataRelationRelease.getVersion());
        checkData.setUpdated(date);
        checkData.setCreated(date);
        checkDataService.insert(checkData);
         tDataRelationRelease.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_OFFLINE);
         tDataRelationRelease.setUpdated(date);
         this.updateById(tDataRelationRelease);
         RespSimpleBody respBody = new RespSimpleBody(tDataRelationRelease.getVersion(), tDataRelationRelease.getXyCode());
         return ReturnDTOUtil.success(respBody);
    }

    @Override
    public ReturnDTO onLine(UpdateStatusParam param) {
        DateTime now = new DateTime();
        Date date = now.toDate();
        //查询xyCode在历史表中，是否存在
        EntityWrapper<TDataRelationHistory> relationReleaseHistory = new EntityWrapper<>();
        relationReleaseHistory.eq(TDataRelationRelease.TB_XY_CODE, param.getXyCode())
                .eq(TDataRelationRelease.TB_STATUS, StatusEnum.VALID);

        TDataRelationHistory tDataRelationReleaseHistory = relationHistoryService.selectOne(relationReleaseHistory);

        if(tDataRelationReleaseHistory == null){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "数据编码不存在");
        }

        DataTypeEnum dataType = tDataRelationReleaseHistory.getDataType();

        //企业信息,短信卡片或短信白名单没有申请上线操作
        if(dataType == DataTypeEnum.ENT || dataType == DataTypeEnum.BUBBLE || dataType == DataTypeEnum.WHITE_LIST){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "数据编码不存在");
        }

        EntityWrapper<TDataRelationRelease> relationRelease = new EntityWrapper<>();
        relationRelease.eq(TDataRelationRelease.TB_XY_CODE, param.getXyCode())
                .eq(TDataRelationRelease.TB_STATUS, StatusEnum.VALID);

        TDataRelationRelease tDataRelationRelease = this.selectOne(relationRelease);
        //xyCode数据在历史表中存在，而在正式表中不存在 或 在正式表中状态不为“已下线”。
        if (tDataRelationRelease == null || tDataRelationRelease.getCheckStatus() != CheckStatusEnum.DATA_STATUS_OFFLINE) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "仅支持已下线数据进行上线操作");
        }

        TOpenCheckData checkData = new TOpenCheckData();
        checkData.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_RELEASE.getStatusCode());
        checkData.setXyCode(tDataRelationRelease.getXyCode());
        checkData.setVersion(tDataRelationRelease.getVersion());
        checkData.setCreated(date);
        checkData.setUpdated(date);
        checkDataService.insert(checkData);

        TDataRelationEdit editbyXyCode = editService.getByXyCode(param.getXyCode());
        //编辑表是否存在相同xyCode，若存在，更新审核状态，版本，时间
        if (editbyXyCode != null){
            editbyXyCode.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_RELEASE);
            editbyXyCode.setVersion(tDataRelationRelease.getVersion());
            editbyXyCode.setUpdated(date);
            editService.updateById(editbyXyCode);
        }else {
            editbyXyCode = new TDataRelationEdit();
            BeanUtils.copyProperties(tDataRelationRelease,editbyXyCode);
            editbyXyCode.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_RELEASE);
            editbyXyCode.setVersion(tDataRelationRelease.getVersion());
            editbyXyCode.setUpdated(date);
            editService.insert(editbyXyCode);
        }
        this.deleteById(tDataRelationRelease.getId());

        RespSimpleBody respBody = new RespSimpleBody(tDataRelationRelease.getVersion(), tDataRelationRelease.getXyCode());
        return ReturnDTOUtil.success(respBody);
    }
}
