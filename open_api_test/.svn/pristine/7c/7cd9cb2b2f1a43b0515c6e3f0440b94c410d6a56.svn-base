package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TDataRelationHistory;
import com.xy.boot.open.entity.TEntInfo;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.mapper.TOpenApiEntInfoMapper;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.EntInfoParam;
import com.xy.boot.open.service.IDataRelationEditService;
import com.xy.boot.open.service.IDataRelationHistoryService;
import com.xy.boot.open.service.IEntInfoService;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * 企业信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class EntInfoServiceImpl extends ServiceImpl<TOpenApiEntInfoMapper, TEntInfo> implements IEntInfoService {

    @Autowired
    private XyCodeUtil xyCodeUtil;

    @Autowired
    private IDataRelationEditService iDataRelationEditService;

    @Autowired
    private IDataRelationHistoryService iDataRelationHistoryService;


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = REQUIRED)
    public ReturnDTO upload(final EntInfoParam param) {
        String xyEntCode = param.getXyEntCode();
        if (OperateTypeEnum.ADD.getType().equals(param.getType())) {
            //新增
            xyEntCode = xyCodeUtil.getXyCode(DataTypeEnum.ENT);
        }
        //更新限制:审核通过后，无法更新企业统一信用注册码
        if (OperateTypeEnum.MODIFY.getType().equals(param.getType())) {
            TDataRelationHistory data = iDataRelationHistoryService.getData(xyEntCode, CheckStatusEnum.DATA_STATUS_OK, DataTypeEnum.ENT);
            if (null != data) {
                TEntInfo info = getInfo(data.getXyCode(), data.getVersion());
                if (null == info) {
                    throw new XyException("参数错误");
                }
                if (!StringUtils.isEmpty(info.getCreditCode()) && !info.getCreditCode().equals(param.getCreditCode())) {
                    log.warn("审核通过后，无法更新企业统一信用注册码");
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "审核通过后，无法更新企业统一信用注册码");
                }
            }
        }
        TEntInfo entInfo = new TEntInfo();
        BeanUtils.copyProperties(param, entInfo);
        entInfo.setXyCode(xyEntCode);
        entInfo.setVersion(XyVersionUtil.getXyVersion());
        entInfo.setXyAgentCode(xyCodeUtil.getAgentCode());
        this.insert(entInfo);
        //更新关联信息
        iDataRelationEditService.updateDataRelation(entInfo.getXyCode(), entInfo.getVersion(), DataTypeEnum.ENT, CheckStatusEnum.DATA_STATUS_SUBMIT);
        return ReturnDTOUtil.success(new RespSimpleBody(entInfo.getVersion(), entInfo.getXyCode()));
    }

    /**
     * 获取信息
     *
     * @param xyCode
     * @param version
     * @return
     */
    private TEntInfo getInfo(String xyCode, String version) {
        if (StringUtils.isEmpty(xyCode) || StringUtils.isEmpty(version)) {
            log.error("参数为空");
            return null;
        }
        EntityWrapper<TEntInfo> qryWrapper = new EntityWrapper<>();
        qryWrapper.eq(TEntInfo.TB_XY_CODE, xyCode);
        qryWrapper.eq(TEntInfo.TB_VERSION, version);
        return this.selectOne(qryWrapper);
    }
}