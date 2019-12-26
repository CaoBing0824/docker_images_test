package com.xy.boot.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.TDataRelationEdit;
import com.xy.boot.open.entity.TSmsInfo;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.TOpenApiDataRelationEditMapper;
import com.xy.boot.open.mapper.TOpenApiMuiltDataMapper;
import com.xy.boot.open.mapper.TOpenApiSmsInfoMapper;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.SmsParseInfoParam;
import com.xy.boot.open.service.ISmsInfoService;
import com.xy.boot.open.util.GenIDUtil;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 短信卡片
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class SmsInfoServiceImpl extends ServiceImpl<TOpenApiSmsInfoMapper, TSmsInfo> implements ISmsInfoService {

    @Autowired
    private TOpenApiDataRelationEditMapper relationEditMapper;

    @Autowired
    private TOpenApiMuiltDataMapper muiltDataMapper;

    @Autowired
    private XyCodeUtil codeUtil;

    @Autowired
    private GenIDUtil idUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO upload(SmsParseInfoParam param) {
//		sceneCode不存在//返回提示，sceneCode不存在
//		xyPubCode不存在//提示xyPubCode无效
        ReturnDTO dto;
        String type = param.getType();
        if (type.equalsIgnoreCase(OperateTypeEnum.ADD.getType())) {
            dto = add(param);
        } else if (type.equalsIgnoreCase(OperateTypeEnum.MODIFY.getType())) {
            dto = modify(param);
        } else if (type.equalsIgnoreCase(OperateTypeEnum.CANCEL.getType())) {
            dto = cancel(param);
        } else {
            dto = ReturnDTOUtil.opCheckError("操作类型无效");
        }
        return dto;
    }

    private ReturnDTO modify(SmsParseInfoParam param) {
        return ReturnDTOUtil.opCheckError("操作类型无效");
    }

    private ReturnDTO add(SmsParseInfoParam param) {
        RespSimpleBody body = insertRelationInfo(param);
        ReturnDTO dto = ReturnDTOUtil.success();
        dto.setMessage(body);
        return dto;
    }

    private ReturnDTO cancel(SmsParseInfoParam param) {
        return ReturnDTOUtil.opCheckError("操作类型无效");
    }

    private RespSimpleBody insertRelationInfo(SmsParseInfoParam param) {
        //		准备入库
        long parseContentRelationId = idUtil.nextId();
        String version = param.getVersion();
        if (StringUtils.isEmpty(version)) {
            version = XyVersionUtil.getXyVersion();
        }

        String xyCode = param.getXySmsCode();
//		更新version即可,否则为插入新数据
        boolean isUpdateReleaseEdit = true;
        if (StringUtils.isEmpty(xyCode)) {
            xyCode = codeUtil.getXyCode(DataTypeEnum.BUBBLE);
            isUpdateReleaseEdit = false;
        }

        Date date = new Date();
//		TODO 用户名
        String username = "Ray Test";

//		封装明细数据
        TSmsInfo smsInfo = new TSmsInfo();
        smsInfo.setXyPubCode(param.getXyPubCode());
        smsInfo.setSmsContent(param.getSmsContent());
        smsInfo.setSmsTemplate(param.getSmsTemplate());
        smsInfo.setParseContent(param.getParseContent());
        smsInfo.setExtMap(param.getExtMap());
        smsInfo.setVersion(version);
        smsInfo.setXyCode(xyCode);
        smsInfo.setStatus(StatusEnum.VALID);
        smsInfo.setCreated(date);
        smsInfo.setUpdated(date);
        smsInfo.setCreatedBy(username);
        smsInfo.setUpdatedBy(username);
        smsInfo.setSceneCode(param.getSceneCode());

        boolean isSuccess = insert(smsInfo);
        if (!isSuccess) {
            log.error("插入数据失败");
            log.error(JSON.toJSONString(param));
            throw new XyException("插入数据失败");
        }

//		TODO 工单id
        long workorderId = -1L;

//		插入编辑表数据
        if (isUpdateReleaseEdit) {
            relationEditMapper.updateDataRelation(
                    xyCode, version, DataTypeEnum.BUBBLE.getType(),
                    workorderId, CheckStatusEnum.DATA_STATUS_SUBMIT.getStatusCode(),
                    StatusEnum.VALID.getStatusCode());
        } else {
            //		获取编辑表数据
            TDataRelationEdit releaseEdit = new TDataRelationEdit();
            releaseEdit.setXyCode(xyCode);
            releaseEdit.setVersion(version);
            releaseEdit.setDataType(DataTypeEnum.BUBBLE);
            releaseEdit.setWorkorderId(workorderId);
            releaseEdit.setCheckStatus(CheckStatusEnum.DATA_STATUS_SUBMIT);
            releaseEdit.setStatus(StatusEnum.VALID);
            releaseEdit.setCreated(date);
            releaseEdit.setUpdated(date);
            releaseEdit.setCreatedBy(username);
            releaseEdit.setUpdatedBy(username);
            Integer releaseInserStatus = relationEditMapper.insert(releaseEdit);
            if (releaseInserStatus == null || releaseInserStatus < 0) {
                log.error("插入数据失败");
                log.error(JSON.toJSONString(param));
                throw new XyException("插入数据失败");
            }
        }

        return new RespSimpleBody(version, xyCode);
    }


    @Override
    public List<TSmsInfo> getListByCode(final String xyPubCode, final String xyCode) {
        if (org.springframework.util.StringUtils.isEmpty(xyCode) || org.springframework.util.StringUtils.isEmpty(xyCode)) {
            return null;
        }
        EntityWrapper<TSmsInfo> ew = new EntityWrapper<>();
        ew.eq(TSmsInfo.TB_XY_PUB_CODE, xyPubCode);
        ew.eq(TSmsInfo.TB_XY_CODE, xyCode);
        return this.selectList(ew);
    }
}