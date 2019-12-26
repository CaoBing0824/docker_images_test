package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.constant.BusinessConstant;
import com.xy.boot.open.entity.TDataRelationHistory;
import com.xy.boot.open.entity.TNumInfo;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.mapper.TOpenApiNumInfoMapper;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.ChannelInfoParam;
import com.xy.boot.open.service.*;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 号码信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class NumInfoServiceImpl extends ServiceImpl<TOpenApiNumInfoMapper, TNumInfo> implements INumInfoService {

    @Autowired
    private XyCodeUtil xyCodeUtil;

    @Autowired
    private IDataRelationEditService iDataRelationEditService;

    @Autowired
    private IMuiltDataService iMuiltDataService;

    @Autowired
    private IDataRelationHistoryService iDataRelationHistoryService;

    @Override
    public ReturnDTO upload(final ChannelInfoParam param) {
        String xyChannelCode = param.getXyChannelCode();
        if (OperateTypeEnum.ADD.getType().equals(param.getType())) {
            //新增
            xyChannelCode = xyCodeUtil.getXyCode(DataTypeEnum.CHANNEL);
        }

        //更新限制:审核通过后，无法更新号码和区域
        if (OperateTypeEnum.MODIFY.getType().equals(param.getType())) {
            TDataRelationHistory data = iDataRelationHistoryService.getData(xyChannelCode, CheckStatusEnum.DATA_STATUS_OK, DataTypeEnum.CHANNEL);
            if (null != data) {
                EntityWrapper<TNumInfo> qryWrapper = new EntityWrapper<>();
                qryWrapper.eq(TNumInfo.TB_XY_CODE, data.getXyCode());
                qryWrapper.eq(TNumInfo.TB_VERSION, data.getVersion());
                TNumInfo info = this.selectOne(qryWrapper);
                if (null == info) {
                    throw new XyException("参数错误");
                }
                if (!StringUtils.isEmpty(info.getReceiveNum()) && !info.getReceiveNum().equals(param.getReceiveNum())) {
                    log.warn("审核通过后，无法更新号码，当前通过号码{}", info.getReceiveNum());
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "审核通过后，无法更新号码，当前通过号码" + info.getReceiveNum());
                }
                String areas = iMuiltDataService.getMuiltDataStr(info.getAreasRelationId());
                if (!StringUtils.isEmpty(areas) && !areas.replaceAll(";", "").equals(param.getAreas().replaceAll(";", ""))) {
                    log.warn("审核通过后，无法更新区域，当前通过区域{}", areas);
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "审核通过后，无法更新区域，当前上线区域" + areas);
                }
            }
        }
        TNumInfo info = new TNumInfo();
        BeanUtils.copyProperties(param, info);
        //保存区域信息
        info.setAreasRelationId(iMuiltDataService.saveMuiltData(new ArrayList<>(Arrays.asList(param.getAreas().split(SysConstVar.ENG_REDNIK)))));
        //保存签名信息
        info.setSignaturesRelationId(iMuiltDataService.saveMuiltData(new ArrayList<>(Arrays.asList(param.getSignatures().split(SysConstVar.ENG_REDNIK)))));
        info.setXyCode(xyChannelCode);
        info.setXyPubCode(param.getXyPubCode());
        info.setVersion(XyVersionUtil.getXyVersion());
        //是否企业专属号码
        String pubExclusive = BusinessConstant.BIZ_NO;//1：是 0：否 默认否
        if(!StringUtils.isEmpty(param.getNumExclusive())){
            pubExclusive = param.getNumExclusive();
        }
        info.setNumExclusive(pubExclusive);
        this.insert(info);
        iDataRelationEditService.updateDataRelation(info.getXyCode(), info.getVersion(), DataTypeEnum.CHANNEL, CheckStatusEnum.DATA_STATUS_SUBMIT);
        return ReturnDTOUtil.success(new RespSimpleBody(info.getVersion(), info.getXyCode()));
    }
}