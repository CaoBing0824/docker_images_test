package com.xy.boot.open.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TYellowInfo;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.YellowInfoParam;
import com.xy.boot.open.service.IYellowInfoService;
import com.xy.boot.open.util.AddressDetectionUtil;
import com.xy.boot.open.util.ParamUtils;
import com.xy.boot.open.util.SensitiveWordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class YellowInfoController {

    @Value("${tellPhoneMinLength}")
    private  int tellPhoneMinLength;

    @Value("${tellPhoneMaxLength}")
    private  int tellPhoneMaxLength;

    @Autowired
    private IYellowInfoService iYellowInfoService;

    @Autowired
    private SensitiveWordUtil sensitivewordUtil;

    @Autowired
    private AddressDetectionUtil addressDetectionUtil;

    @PostMapping({"/yellowPageInfoUpload"})
    public ReturnDTO channelsUpload(@RequestBody @Valid YellowInfoParam param, BindingResult mesResult) {
        //type=ADD，xyEntCode为空时，提示改为，新增时，xyEntCode要为空
        String errorMsg = ParamUtils.validCodeAndType(param.getXyYellowCode(), param.getType(), DataTypeEnum.YELLOW_PAGE.getDesc());
        if (!StringUtils.isEmpty(errorMsg)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), errorMsg);
        }
        //参数验证
        if (mesResult.hasErrors()) {
            log.info("[已拒收]公众号编码:{}, 通道号编码: {}, 校验结果:{}",param.getXyPubCode(), param.getXyYellowCode(), mesResult.getFieldError().getDefaultMessage());
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //验证修改时黄页编号是否在公众号下
        if (OperateTypeEnum.MODIFY.getType().equalsIgnoreCase(param.getType()) ) {
            EntityWrapper<TYellowInfo> ew = new EntityWrapper<>();
            ew.eq(TYellowInfo.TB_XY_PUB_CODE, param.getXyPubCode());
            ew.eq(TYellowInfo.TB_XY_CODE, param.getXyYellowCode());
            Integer count = iYellowInfoService.selectCount(ew);
            if (count == null || count == 0) {
                return ReturnDTOUtil.opCheckError("该黄页号不存在于该公众号下");
            }
        }
        //校验服务热线字段数据格式
        if (!checkYellowTellPhoneNum(param.getTellPhone())) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),  "服务热线由数字或“-”组成，数字长度不能低于"+tellPhoneMinLength+"位数，总长度不能超过"+tellPhoneMaxLength+"，且不能为手机号码！");
        }
        // 检查敏感词
        String sloganCheckResult = sensitivewordUtil.validSensitiveWord( param.getSlogan() );
        String introductionCheckResult = sensitivewordUtil.validSensitiveWord( param.getIntroduction() );
        String rangeCheckResult = sensitivewordUtil.validSensitiveWord( param.getRange() );
        String openTimeResult = sensitivewordUtil.validSensitiveWord( param.getOpenTime() );
        StringBuilder sensitiveCheckResult = new StringBuilder();
        if( !StringUtils.isEmpty(sloganCheckResult)){
            sensitiveCheckResult.append("企业口号").append(sloganCheckResult);
        }
        if( !StringUtils.isEmpty(introductionCheckResult)){
            sensitiveCheckResult.append("企业介绍").append(introductionCheckResult);
        }
        if( !StringUtils.isEmpty(rangeCheckResult)){
            sensitiveCheckResult.append("经营范围").append(rangeCheckResult);
        }
        if( !StringUtils.isEmpty(openTimeResult)){
            sensitiveCheckResult.append("营业时间").append(openTimeResult);
        }
        if( !StringUtils.isEmpty(sensitiveCheckResult.toString())){
            log.info("[已拒收]公众号编码:{}, 通道号编码: {}, 敏感词校验结果:{}",param.getXyPubCode(), param.getXyYellowCode(), sensitiveCheckResult.toString());
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), sensitiveCheckResult.toString());
        }

        // 检查地址搜索关键词
        boolean validAddressResult = addressDetectionUtil.validAddress(param.getSerchKey());
        if( !validAddressResult ){
            log.info("[已拒收]公众号编码:{}, 通道号编码: {}, 地址搜索关键词 : {}, 地址检查结果 : 地址搜索关键词无法搜索到位置", param.getXyPubCode(), param.getXyYellowCode(),param.getSerchKey());
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "地址搜索关键词无法搜索到位置");
        }

        try {
            return iYellowInfoService.upload(param);
        } catch (Exception e) {
            log.error("异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }


    /**
     * 校验服务热线字段数据格式
     *
     * @param tellPhone
     * @return
     */
    private boolean checkYellowTellPhoneNum(String tellPhone) {
        String  tellPhoneNums = tellPhone.replaceAll("-","");
        Pattern pattern = Pattern.compile("[0-9]+");
        Pattern patternMobile = Pattern.compile("1\\d{10}");
        Matcher isNum = pattern.matcher(tellPhoneNums);
        Matcher isMobileNum = patternMobile.matcher(tellPhone);
        if (!isNum.matches()) {
            return false;
        }
        if(tellPhoneNums.length() < tellPhoneMinLength){
            return false;
        }
        if(tellPhone.length() > tellPhoneMaxLength){
            return false;
        }
        if(isMobileNum.matches()){
            return false;
        }
        return true;
    }
}