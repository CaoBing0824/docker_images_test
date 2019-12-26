package com.xy.boot.open.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.constant.BusinessConstant;
import com.xy.boot.open.entity.TPubInfo;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.IndustryInfoStatusEnum;
import com.xy.boot.open.entity.enums.ProductTypeEnum;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.*;
import com.xy.boot.open.service.IDataRelationHistoryService;
import com.xy.boot.open.service.IOpenIndustryInfoService;
import com.xy.boot.open.service.IPubExtInfoService;
import com.xy.boot.open.service.IPubInfoService;
import com.xy.boot.open.util.ParamUtils;
import com.xy.boot.open.util.SensitiveWordUtil;
import com.xy.boot.open.util.StringLengthVaildUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 公众号信息
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class PubInfoController {

    @Value("${maximumOfPubCodes}")
    private  int maximumOfPubCodes;

    @Autowired
    private IDataRelationHistoryService iDataRelationHistoryService;

    @Autowired
    private IPubExtInfoService iPubExtInfoService;

    @Autowired
    private IPubInfoService iPubInfoService;

    @Autowired
    private SensitiveWordUtil sensitivewordUtil;

    @Autowired
    private IOpenIndustryInfoService iOpenIndustryInfoService;

    @PostMapping({"/pubInfoUpload"})
    public ReturnDTO pubInfoUpload(@RequestBody @Valid PubInfoParam param, BindingResult mesResult) {
        //type=ADD，xyEntCode为空时，提示改为，新增时，xyEntCode要为空
        String errorMsg = ParamUtils.validCodeAndType(param.getXyPubCode(), param.getType(), DataTypeEnum.PUB.getDesc());
        if (!StringUtils.isEmpty(errorMsg)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), errorMsg);
        }
        //参数验证
        if (mesResult.hasErrors()) {
            log.info("[已拒收] 企业编码: {} 公众号名称: {}, 校验结果:{}",param.getXyEntCode(), param.getPubName(), mesResult.getFieldError().getDefaultMessage());
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //验证修改时公众号是否在企业号下
        if (OperateTypeEnum.MODIFY.getType().equalsIgnoreCase(param.getType()) ) {
            EntityWrapper<TPubInfo> ew = new EntityWrapper<>();
            ew.eq(TPubInfo.TB_XY_ENT_CODE, param.getXyEntCode());
            ew.eq(TPubInfo.TB_XY_CODE, param.getXyPubCode());
            Integer count = iPubInfoService.selectCount(ew);
            if (count == null || count == 0) {
                return ReturnDTOUtil.opCheckError("该公众号不存在于该企业号下");
            }
            //公众号处于待验收状态不能支持修改公众号信息
            String status = iPubExtInfoService.getAcceptanceStatusByPubCode(param.getXyPubCode());
            String msg = "";
            if(BusinessConstant.ACCEPTANCE_STAY.equals(status)){
                msg = "当前公众号处于待验收状态,不支持修改公众号信息";
                return ReturnDTOUtil.opCheckError(msg);
            }
        }
        // 公众号类型为yellow时
        if( ProductTypeEnum.YELLOW.getType().equals( param.getProductType() )){

            // 对公众号名称长度进行检查
            final int criticalLength = 16;
            StringBuilder pubCheckResultInfo = new StringBuilder();
            boolean allCheckPass = true; // 默认检查通过，值为 false 表示有检查不通过
            boolean pubNameLengthCheckResult = StringLengthVaildUtil.isLte(criticalLength, param.getPubName());
            pubCheckResultInfo.append("公众号名称：");
            if( !pubNameLengthCheckResult ){
                allCheckPass = false;
                pubCheckResultInfo.append("长度不能超过" + criticalLength + "个字符，一个汉字占两个字符；");
            }

            // 对公众号名称进行敏感词检查
            String pubNameCheckResult = sensitivewordUtil.validSensitiveWord( param.getPubName() );
            if( !StringUtils.isEmpty(pubNameCheckResult)){
                allCheckPass = false;
                pubCheckResultInfo.append(pubNameCheckResult).append("；");
            }

            // 如果上述检查通过，重置提示信息
            if( allCheckPass ){
                pubCheckResultInfo = new StringBuilder();
            }

            // 对公众号行业编码进行检查
            final String prefixInfo = "公众号类型为：" + ProductTypeEnum.YELLOW.getType();
            if( !StringUtils.isEmpty(param.getIndustryCode())){
                boolean industryCodeCheck = iOpenIndustryInfoService.checkIndustryCode(param.getIndustryCode(), IndustryInfoStatusEnum.PERMISSION_YELLOW_REPORT);
                if( !industryCodeCheck ){
                    allCheckPass = false;
                    pubCheckResultInfo.append( prefixInfo + "，不支持上报当前行业: ").append(param.getIndustryCode());
                }
            } else {
                allCheckPass = false;
                pubCheckResultInfo.append( prefixInfo + "，公众号行业编码不能为空");
            }

            if( !allCheckPass ){
                log.info("[已拒收] 企业编码: {} 公众号名称: {}, 校验结果:{} ",param.getXyEntCode(), param.getPubName(), pubCheckResultInfo.toString());
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), pubCheckResultInfo.toString());
            }

        }

        try {
            return iPubInfoService.upload(param);
        } catch (Exception e) {
            log.error("异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }

    //客户发起公众号续约
    @PostMapping({"/pubInfoRenewContract"})
    public ReturnDTO pubInfoRenewContract(@RequestBody @Valid PubInfoRenewalContractParam param, BindingResult mesResult) {
        log.info("公众号续约开始");
        //参数验证
        if (mesResult.hasErrors()) {
            log.info("[公众号续约申请] 企业编码: {} 公众号名称: {}, 校验结果:{}",param.getXyPubCode(), mesResult.getFieldError().getDefaultMessage());
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //校验日期格式有效性
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date newExpiryDate = null;
        try{
            formatter.setLenient(false);
            newExpiryDate = formatter.parse(param.getNewExpiryDate());
        }catch(Exception e){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "日期格式不正确，yyyy-MM-dd");
        }
        //公众号是否存在
        TPubInfo pubInfo = iPubInfoService.getOne(param.getXyPubCode());
        if(null == pubInfo){
            return ReturnDTOUtil.custom(HttpCodeEnum.INVALID_REQUEST.getCode(),"","公众号：【"+param.getXyPubCode()+"】不存在,请确认参数信息");
        }
        try {
            return iPubInfoService.renewContract(param,newExpiryDate);
        } catch (Exception e) {
            log.error("公众号续约异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }

    //通知客户发起公众号续约的结果
    @PostMapping({"/renewContractNotice"})
    public ReturnDTO pubInfoRenewContractNotice(@RequestBody @Valid PubInfoRenewalQueryParam param, BindingResult mesResult) {
        log.info("公众号续约通知开始");
        //参数验证
        if (mesResult.hasErrors()) {
            log.info("[公众号续约申请] 公众号名称: {}, 校验结果:{}",param.getXyPubCode(), mesResult.getFieldError().getDefaultMessage());
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        String[]  pubCodes = param.getXyPubCode().split(SysConstVar.ENG_REDNIK);
        try {
            return iPubInfoService.renewContractNotice(pubCodes);
        } catch (Exception e) {
            log.error("公众号续约通知异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }

    //公众号发起验收确认
    @PostMapping({"/pubInfoAcceptanceConfirm"})
    public ReturnDTO publicInfoAcceptanceConfirm(@RequestBody @Valid PubInfoAcceptanceConfirmParam param, BindingResult mesResult) {
        try {
            //参数验证
            if (mesResult.hasErrors()) {
                log.info("[已拒验收确认] 校验结果:{}", mesResult.getFieldError().getDefaultMessage());
                return ReturnDTOUtil.opCheckError(mesResult);
            }
            //校验确认信息是否正常
            if(null == param.getPubInfoConfirmInfos() ||  param.getPubInfoConfirmInfos().size() == 0){
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),"公众号验收参数不能为空");
            }
            //一次最多确认
            if(param.getPubInfoConfirmInfos().size() > maximumOfPubCodes){
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),"公众号验收个数不能超过"+maximumOfPubCodes+"个");
            }
            //校验公众号是否有效
            for(PubInfoConfirmParam item : param.getPubInfoConfirmInfos()){
                String pubCode = item.getPubCode();
                String bizRet = item.getBizRet();
                String desc = item.getDesc();
                if(BusinessConstant.BIZ_NO.equals(bizRet) && StringUtils.isEmpty(desc)){
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),"公众号【"+pubCode+"】验收不通过时，不通过原因不能为空");
                }

                if (!iDataRelationHistoryService.checkXyCode(pubCode,DataTypeEnum.PUB)) {
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),"公众号【"+pubCode+"】编码无效");
                }
            }
            //校验状态
            for(PubInfoConfirmParam item : param.getPubInfoConfirmInfos()){
                String pubCode = item.getPubCode();
                String bizRet = item.getBizRet();
                String desc = item.getDesc();

                //待验收状态才可以验收确认
                String status = iPubExtInfoService.getAcceptanceStatusByPubCode(pubCode);
                String msg = "";
                if(StringUtils.isEmpty(status) || BusinessConstant.ACCEPTANCE_NOT_YEY_ARRIVED.equals(status)){
                    msg = "公众号【"+pubCode+"】公众号还未上线，不支持验收";
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),msg,null);
                }
                if(BusinessConstant.ACCEPTANCE_THROUGH.equals(status)){
                    msg = "公众号【"+pubCode+"】验收已通过，请勿重复验收";
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),msg,iPubInfoService.structuredAcceptanceResultObj(pubCode));
                }
                if(BusinessConstant.ACCEPTANCE_NOT_THROUGH.equals(status)){
                    msg = "公众号【"+pubCode+"】验收未通过，请勿重复验收";
                    return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),msg,null);
                }
            }
                return iPubInfoService.initiateAcceptanceConfirm(param);
        } catch (Exception e) {
            log.error("公众号验收确认异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }
}