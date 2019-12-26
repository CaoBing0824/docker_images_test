package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.basic.common.utility.DateUtil;
import com.xy.basic.common.utility.HttpClientUtil;
import com.xy.basic.common.utility.JsonUtil;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.constant.BusinessConstant;
import com.xy.boot.open.entity.TDataRelationRelease;
import com.xy.boot.open.entity.TOpenLogoInfo;
import com.xy.boot.open.entity.TOpenRenewalInfo;
import com.xy.boot.open.entity.TPubInfo;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.ProductTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.TOpenApiPubExtInfoMapper;
import com.xy.boot.open.mapper.TOpenApiPubInfoMapper;
import com.xy.boot.open.mapper.TOpenApiPubRenewalInfoMapper;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.*;
import com.xy.boot.open.service.IDataRelationEditService;
import com.xy.boot.open.service.IOpenLogoInfoService;
import com.xy.boot.open.service.IPubInfoService;
import com.xy.boot.open.service.IUpOffLineService;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class PubInfoServiceImpl extends ServiceImpl<TOpenApiPubInfoMapper, TPubInfo> implements IPubInfoService {

    @Autowired
    private TOpenApiPubExtInfoMapper tOpenApiPubExtInfoMapper;

    @Autowired
    private TOpenApiPubRenewalInfoMapper tOpenApiPubRenewalInfoMapper;

    @Autowired
    private XyCodeUtil xyCodeUtil;

    @Autowired
    private IDataRelationEditService iDataRelationEditService;

    @Autowired
    private IOpenLogoInfoService iOpenLogoInfoService;

    @Autowired
    IUpOffLineService upOffLineService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO upload(final PubInfoParam param) {
        Date date = new Date();
        String xyPubCode = param.getXyPubCode();
        String xyLogoCode = null;
        if (OperateTypeEnum.ADD.getType().equals(param.getType())) {
            //新增-生成XyCode
            xyPubCode = xyCodeUtil.getXyCode(DataTypeEnum.PUB);

        }
        TPubInfo info = new TPubInfo();
        BeanUtils.copyProperties(param, info);
        info.setXyCode(xyPubCode);
        info.setXyEntCode(param.getXyEntCode());
        info.setVersion(XyVersionUtil.getXyVersion());
        info.setProductType(ProductTypeEnum.getEnum(param.getProductType()));
        //新增合作月
        if(StringUtils.isNotEmpty( param.getCooperativeMonth())){
            int cooperativeMonth = Integer.parseInt(param.getCooperativeMonth());
            info.setCooperativeMonth(cooperativeMonth);
        }
        this.insert(info);

        // 新增或更新公众号的审核数据
        iDataRelationEditService.updateDataRelation(info.getXyCode(), info.getVersion(), DataTypeEnum.PUB, CheckStatusEnum.DATA_STATUS_SUBMIT);

        // 新增或更新公众号logo的审核数据
        if( StringUtils.isNotEmpty( param.getShowLogoWhite() )
                || StringUtils.isNotEmpty( param.getShowLogoColor() ) ){

            // 新增公众号带logo信息的,直接生成code
            if(OperateTypeEnum.ADD.getType().equals(param.getType())){
                xyLogoCode = xyCodeUtil.getXyCode(DataTypeEnum.LOGO);
            } else if(OperateTypeEnum.MODIFY.getType().equals(param.getType())){
                TOpenLogoInfo oldLogoInfo = iOpenLogoInfoService.getOneByXyPubCode(xyPubCode);
                if( oldLogoInfo != null ){
                    // 修改公众号时,已存在logo信息
                    xyLogoCode = oldLogoInfo.getXyCode();
                } else {
                    // 修改公众号时,初次上传logo信息
                    xyLogoCode = xyCodeUtil.getXyCode(DataTypeEnum.LOGO);
                }
            }

            TOpenLogoInfo tOpenLogoInfo = new TOpenLogoInfo();
            tOpenLogoInfo.setShowLogoColor(param.getShowLogoColor());
            tOpenLogoInfo.setShowLogoWhite(param.getShowLogoWhite());
            tOpenLogoInfo.setVersion(XyVersionUtil.getXyVersion());
            tOpenLogoInfo.setXyPubCode(xyPubCode);
            tOpenLogoInfo.setXyCode(xyLogoCode);
            tOpenLogoInfo.setCreated(date);
            tOpenLogoInfo.setUpdated(date);
            tOpenLogoInfo.setStatus(CheckStatusEnum.DATA_STATUS_SUBMIT.getStatusCode());
            iOpenLogoInfoService.insert(tOpenLogoInfo);
            iDataRelationEditService.updateDataRelation(xyLogoCode, tOpenLogoInfo.getVersion(), DataTypeEnum.LOGO, CheckStatusEnum.DATA_STATUS_SUBMIT);
        }

        return ReturnDTOUtil.success(new RespSimpleBody(info.getVersion(), info.getXyCode()));
    }

    @Override
    public List<TPubInfo> getInfo(final String pubCode) {
        EntityWrapper<TPubInfo> pubEW = new EntityWrapper<>();
        pubEW.eq(TPubInfo.TB_XY_CODE, pubCode);
        pubEW.last(" limit 1 ");
        return this.selectList(pubEW);
    }

    @Override
    public TPubInfo getOne(final String pubCode) {
        EntityWrapper<TPubInfo> pubEW = new EntityWrapper<>();
        pubEW.eq(TPubInfo.TB_XY_CODE, pubCode);
        return this.selectOne(pubEW);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO renewContract(PubInfoRenewalContractParam param,Date date) {
        log.info("发起续约开始");
        String pubCode = param.getXyPubCode();
        //查询已上线或者已到期
        EntityWrapper<TDataRelationRelease> relationRelease = new EntityWrapper<>();
        relationRelease.eq(TDataRelationRelease.TB_XY_CODE, pubCode)
                .eq(TDataRelationRelease.TB_STATUS, StatusEnum.VALID);
        TDataRelationRelease tDataRelationRelease = upOffLineService.selectOne(relationRelease);
        //已上线 待下线 已过期 才可发起续约
        if (tDataRelationRelease == null || (tDataRelationRelease.getCheckStatus() != CheckStatusEnum.DATA_STATUS_RELEASE
                && tDataRelationRelease.getCheckStatus() != CheckStatusEnum.DATA_STATUS_TIMEOUT
                && tDataRelationRelease.getCheckStatus() != CheckStatusEnum.DATA_STATUS_CONFIRM_OFFLINE)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.NOT_FOUND.getCode(),"","公众号：【"+pubCode+"】暂不满足续约条件,请确认参数信息");
        }
        //判断是否已经发起续约且处于审核中
        String id = tOpenApiPubRenewalInfoMapper.getRenewalByPStatusAndBizCode(pubCode,"pubXy","-1");
        if(id != null){
            //更新之前续约记录为已失效
            tOpenApiPubRenewalInfoMapper.updateRenewalByPubCode(0,id);
        }
        //新合作到期日期（必须大于当前日期）
        Long nowDate = new Date().getTime();
        if (date.getTime() > nowDate) {
            //获取公众号失效日期
            Date expiryDate = tOpenApiPubExtInfoMapper.getExpiryDateByPubCode(pubCode);
            //插入初始续约记录信息
            TOpenRenewalInfo renewalInfo  = new TOpenRenewalInfo();
            renewalInfo.setXyCode(pubCode);
            renewalInfo.setBizCode(BusinessConstant.BIZ_XY);
            renewalInfo.setBizStartTime(new Date());
            renewalInfo.setOrgExpiryDate(expiryDate);
            renewalInfo.setNewExpiryDate(date);
            renewalInfo.setBizResult(BusinessConstant.BIZ_DD);//待审核 0 审核不通过  1 审核通过  -1待审核
            renewalInfo.insert();
            return ReturnDTOUtil.custom(HttpCodeEnum.OK.getCode(),"","发起续约成功,待审核");
        }else {
            log.info("失败-发起续约结束");
            return ReturnDTOUtil.custom(HttpCodeEnum.FAIL.getCode(),HttpCodeEnum.FAIL.getMessage(),"公众号到期日期需大于当前日期");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO renewContractNotice(String[] pubCodes) {
        List<PubInfoConfirmParam>  results = new ArrayList<PubInfoConfirmParam>();
        for(String pubCode : pubCodes){
            if(StringUtils.isNotEmpty(pubCode)){
                PubInfoConfirmParam pubInfoConfirmParam = new PubInfoConfirmParam();
                TOpenRenewalInfo renewalInfo =  tOpenApiPubRenewalInfoMapper.getRenewalByPubCode(pubCode);
                if(renewalInfo != null){
                    pubInfoConfirmParam.setPubCode(renewalInfo.getXyCode());
                    pubInfoConfirmParam.setBizRet(renewalInfo.getBizResult());
                    pubInfoConfirmParam.setDesc(renewalInfo.getBizDesc());
                }else{
                    pubInfoConfirmParam.setPubCode(pubCode);
                    pubInfoConfirmParam.setDesc("未发现续约信息");
                }
                results.add(pubInfoConfirmParam);
            }
        }
        return ReturnDTOUtil.success(results);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO initiateAcceptanceConfirm(PubInfoAcceptanceConfirmParam param) {
        List<PubInfoAcceptanceResultParam> lists = new ArrayList<>();
        for(PubInfoConfirmParam item : param.getPubInfoConfirmInfos()){
            //更新验收状态
            String pubCode = item.getPubCode();
            tOpenApiPubExtInfoMapper.updateAcceptanceStatusByPubCode(pubCode,item.getDesc(),
                    item.getBizRet().equals(BusinessConstant.BIZ_YES) ? BusinessConstant.ACCEPTANCE_THROUGH : BusinessConstant.ACCEPTANCE_NOT_THROUGH);
            //返回失效日期
            if(item.getBizRet().equals(BusinessConstant.BIZ_YES)){
                lists.add(this.structuredAcceptanceResultObj(pubCode));
            }
        }
        return ReturnDTOUtil.success(lists);
    }

    @Override
    public PubInfoAcceptanceResultParam structuredAcceptanceResultObj(String pubCode){
        String formatDate = "";
        PubInfoAcceptanceResultParam rt = new PubInfoAcceptanceResultParam();
        Date expiryDate = tOpenApiPubExtInfoMapper.getExpiryDateByPubCode(pubCode);
        if(null != expiryDate){
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            formatDate = dFormat.format(expiryDate);
        }
        rt.setXyPubCode(pubCode);
        rt.setExpiryDate(formatDate);
        return rt;
    }

}