package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.TActionInfo;
import com.xy.boot.open.entity.TPubInfo;
import com.xy.boot.open.entity.TSmsInfo;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.ActionInfoParam;
import com.xy.boot.open.service.IActionInfoService;
import com.xy.boot.open.service.IBrandInfoService;
import com.xy.boot.open.service.IPubInfoService;
import com.xy.boot.open.service.ISmsInfoService;
import com.xy.boot.open.util.ParamUtils;
import com.xy.boot.open.util.XyAgentCodeHolder;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 短信服务上报
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class ActionInfoController {
    @Value("${maximumOfBrandCodes}")
    private  int maximumOfBrandCodes;

    @Value("${maximumOfBrandLength}")
    private  int maximumOfBrandLength;

    @Autowired
    private IActionInfoService service;

    @Autowired
    private XyAgentCodeHolder codeHolder;

    @Autowired
    private ISmsInfoService smsInfoService;

    @Autowired
    private IPubInfoService pubInfoService;

    @Autowired
    private IBrandInfoService brandInfoService;

    @PostMapping({"/actionInfoUpload"})
    public ReturnDTO actionInfoUpload(@RequestBody @Valid ActionInfoParam param, BindingResult mesResult) {
        //返回对象必须通过 ReturnDTO
        ReturnDTO returnDTO;

        //入参日志
        log.info(JSON.toJSONString(param));
        //参数验证
        String errorMsg = ParamUtils.validCodeAndType(param.getXyBtnCode(), param.getType(), DataTypeEnum.BUBBLE_BTN.getDesc());
        if (!StringUtils.isEmpty(errorMsg)) {
            return ReturnDTOUtil.opCheckError(errorMsg);
        }
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //验证修改时动作编号是否在公众号下
        if (OperateTypeEnum.MODIFY.getType().equalsIgnoreCase(param.getType()) ) {
            EntityWrapper<TActionInfo> ew = new EntityWrapper<>();
            ew.eq(TActionInfo.XY_PUB_CODE, param.getXyPubCode());
            ew.eq(TActionInfo.TB_XY_CODE, param.getXyBtnCode());
            Integer count = service.selectCount(ew);
            if (count == null || count == 0) {
                return ReturnDTOUtil.opCheckError("该短信服务编码不存在于该公众号下");
            }
        }
        //校验品牌参数
        if (!org.springframework.util.StringUtils.isEmpty(param.getBrandCode())) {
            String[] brands = param.getBrandCode().split(SysConstVar.ENG_REDNIK);
            if (brands.length == 0) {//处理
                param.setBrandCode("");
            }else{
                //去重
                StringBuilder sb = new StringBuilder();
                List listTemp = new ArrayList();
                for(int i=0;i<brands.length;i++){
                    if(!listTemp.contains(brands[i])){
                        listTemp.add(brands[i]);
                        sb.append(brands[i]).append(SysConstVar.ENG_REDNIK);
                    }
                }
                param.setBrandCode(sb.toString());
            }
        }
        String isBrandCodeValid = brandInfoService.isBrandCodeValid(param.getBrandCode(),maximumOfBrandCodes,maximumOfBrandLength);
        if(null != isBrandCodeValid){
            return ReturnDTOUtil.custom(HttpCodeEnum.INVALID_REQUEST.getCode(), isBrandCodeValid);
        }

        //判断品牌是否在品牌列表中
        List<String> notExists = brandInfoService.checkBrandExists(param.getBrandCode());
        if(null != notExists){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "品牌编码"+notExists.toString()+"不存在");
        }

        if (!StringUtils.isEmpty(param.getStartTime()) && !StringUtils.isEmpty(param.getEndTime())) {
            if (!DateTime.parse(param.getStartTime()).isBefore(DateTime.parse(param.getEndTime()))) {
                log.warn("数据生效时间须小于失效时间");
                return ReturnDTOUtil.opCheckError("数据生效时间须小于失效时间");
            }
        }
        /**start SERVER-761 判断短信编码是否属于公众号 heyuancheng**/
        if (!org.springframework.util.StringUtils.isEmpty(param.getXySmsCode())) {
            List<TSmsInfo> listByCode = smsInfoService.getListByCode(param.getXyPubCode(), param.getXySmsCode());
            if (CollectionUtils.isEmpty(listByCode)) {
                List<TPubInfo> info = pubInfoService.getInfo(param.getXyPubCode());
                if (CollectionUtils.isEmpty(info)) {
                    return ReturnDTOUtil.opCheckError("公众号编码无效");
                } else {
                    return ReturnDTOUtil.opCheckError("短信编码不属于公众号 " + info.get(0).getPubName());
                }
            }
        }
        /**end SERVER-761 判断短信编码是否属于公众号 heyuancheng**/
        try {
            param.setXyAgentCode(codeHolder.getAgentCode());
            returnDTO = service.actionInfoUpload(param);
        } catch (XyException ex) {
            returnDTO = ex.getReturnDTO("保存异常");
        }
        //出参日志
        log.info(JSON.toJSONString(returnDTO));
        return returnDTO;
    }
}