package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.*;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.TOpenApiDataRelationHistoryMapper;
import com.xy.boot.open.model.bo.CheckInfoQueryBo;
import com.xy.boot.open.model.params.CheckInfoQueryParam;
import com.xy.boot.open.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CheckInfoQueryServiceImpl extends ServiceImpl<TOpenApiDataRelationHistoryMapper, TDataRelationHistory> implements ICheckInfoQueryService {

    @Autowired
    private IWhiteTipsInfoService WhiteTipsInfoService;

    @Autowired
    private ICheckDataService checkDataService;

    @Autowired
    private IDataRelationReleaseService relationsReleaseService;

    @Autowired
    private IDataRelationEditService relationEditService;

    @Autowired
    private IDataRelationHistoryService relationHistoryService;

    @Override
    public ReturnDTO getCheckInfoQueryStatus(CheckInfoQueryParam param) {
        List<CheckInfoQueryBo> list = new ArrayList<>();

        EntityWrapper<TOpenCheckData> tOpenCheckDataEntityWrapper = new EntityWrapper<>();
        TDataRelationRelease tDataRelationRelease = null;
        TDataRelationEdit tDataRelationEdit = null;

        EntityWrapper<TDataRelationRelease> releaseEntityWrapper = new EntityWrapper<>();
        releaseEntityWrapper.eq(TDataRelationRelease.TB_XY_CODE, param.getXyCode())
                .eq(TDataRelationRelease.TB_STATUS, StatusEnum.VALID);

        tDataRelationRelease = relationsReleaseService.selectOne(releaseEntityWrapper);

        EntityWrapper<TDataRelationEdit> editEntityWrapper = new EntityWrapper<>();
        editEntityWrapper.eq(TDataRelationEdit.TB_XY_CODE, param.getXyCode())
                .eq(TDataRelationEdit.TB_STATUS, StatusEnum.VALID);

        tDataRelationEdit = relationEditService.selectOne(editEntityWrapper);

        if (tDataRelationEdit != null){
            //菜单，按钮，卡片, 关联关系 若为白名单上线，根据xyCode查询t_open_white_tips_info，判断是否为1，若为1：白名单上线，若为0：审核通过
            if ((tDataRelationEdit.getDataType() == DataTypeEnum.MENU
                    || tDataRelationEdit.getDataType() == DataTypeEnum.BUBBLE
                    || tDataRelationEdit.getDataType() == DataTypeEnum.BUBBLE_BTN
                    || tDataRelationEdit.getDataType() == DataTypeEnum.MENU_RELATION)
                    && tDataRelationEdit.getCheckStatus().equals(CheckStatusEnum.DATA_STATUS_WHITE_MENU_RELEASE)){

                EntityWrapper<TOpenWhiteTipsInfo> tOpenWhiteTipsInfoEntityWrapper = new EntityWrapper<>();
                tOpenWhiteTipsInfoEntityWrapper.eq(TOpenWhiteTipsInfo.TB_XY_CODE, tDataRelationEdit.getXyCode())
                        .eq(TOpenWhiteTipsInfo.TB_VERSION, tDataRelationEdit.getVersion())
                        .eq(TOpenWhiteTipsInfo.TB_STATUS, StatusEnum.VALID);
                TOpenWhiteTipsInfo tOpenWhiteTipsInfo = WhiteTipsInfoService.selectOne(tOpenWhiteTipsInfoEntityWrapper);
                //判断是否为二次白名单上线
                EntityWrapper<TDataRelationHistory> historyEntityWrapper = new EntityWrapper<>();
                historyEntityWrapper.eq(TDataRelationHistory.TB_XY_CODE, tDataRelationEdit.getXyCode())
                        .eq(TDataRelationHistory.TB_VERSION, tDataRelationEdit.getVersion())
                        .eq(TDataRelationHistory.TB_CHECK_STATUS, CheckStatusEnum.DATA_STATUS_RELEASE)
                        .eq(TDataRelationHistory.TB_STATUS, StatusEnum.VALID);
                TDataRelationHistory tDataRelationHistory = relationHistoryService.selectOne(historyEntityWrapper);
                //若为二次白名单上线，且tips为0，返回待上线
                if (tOpenWhiteTipsInfo != null && tOpenWhiteTipsInfo.getWhiteTips().equals(0)){
                    if (tDataRelationHistory == null)
                        tDataRelationEdit.setCheckStatus(CheckStatusEnum.DATA_STATUS_OK);
                    else
                        tDataRelationEdit.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_RELEASE);
                }
            }

            CheckInfoQueryBo editData = new CheckInfoQueryBo();
            tOpenCheckDataEntityWrapper.eq(TOpenCheckData.TB_XY_CODE, tDataRelationEdit.getXyCode())
                    .eq(TOpenCheckData.TB_VERSION, tDataRelationEdit.getVersion())
                    .eq(TOpenCheckData.TB_CHECK_STATUS, tDataRelationEdit.getCheckStatus().getStatusCode())
                    .eq(TOpenCheckData.TB_STATUS, StatusEnum.VALID);
            TOpenCheckData editCheckData = checkDataService.selectOne(tOpenCheckDataEntityWrapper);
            if (editCheckData != null){
                editData.setRemark(editCheckData.getRemark());   //审核备注
            }
            editData.setDataType(tDataRelationEdit.getDataType().getType());
            editData.setStatus(String.valueOf(tDataRelationEdit.getCheckStatus().getStatusCode()));
            editData.setStatusStr(tDataRelationEdit.getCheckStatus().getStatusDesc());
            editData.setXyCode(tDataRelationEdit.getXyCode());
            editData.setXyVersion(tDataRelationEdit.getVersion());
            list.add(editData);
        }

        if (tDataRelationRelease != null){
            //菜单，按钮若为白名单下线，更改查询审核状态为待下线
            if ((tDataRelationRelease.getDataType() == DataTypeEnum.MENU  || tDataRelationRelease.getDataType() == DataTypeEnum.BUBBLE_BTN)
                    && tDataRelationRelease.getCheckStatus().equals(CheckStatusEnum.DATA_STATUS_WHITE_MENU_OFFLINE)){
                tDataRelationRelease.setCheckStatus(CheckStatusEnum.DATA_STATUS_CONFIRM_OFFLINE);
            }

            CheckInfoQueryBo releaseData = new CheckInfoQueryBo();
            tOpenCheckDataEntityWrapper.eq(TOpenCheckData.TB_XY_CODE, tDataRelationRelease.getXyCode())
                    .eq(TOpenCheckData.TB_VERSION, tDataRelationRelease.getVersion())
                    .eq(TOpenCheckData.TB_CHECK_STATUS, tDataRelationRelease.getCheckStatus().getStatusCode())
                    .eq(TOpenCheckData.TB_STATUS, StatusEnum.VALID);
            TOpenCheckData releaseCheckData = checkDataService.selectOne(tOpenCheckDataEntityWrapper);
            if (releaseCheckData != null){
                releaseData.setRemark(releaseCheckData.getRemark());   //审核备注
            }
            releaseData.setDataType(tDataRelationRelease.getDataType().getType());
            releaseData.setStatus(String.valueOf(tDataRelationRelease.getCheckStatus().getStatusCode()));
            releaseData.setStatusStr(tDataRelationRelease.getCheckStatus().getStatusDesc());
            releaseData.setXyCode(tDataRelationRelease.getXyCode());
            releaseData.setXyVersion(tDataRelationRelease.getVersion());
            list.add(releaseData);
        }
        return ReturnDTOUtil.success(list);
    }
}
