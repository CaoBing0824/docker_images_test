package com.xy.boot.open.controller;

import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.params.EntInfoParam;
import com.xy.boot.open.service.IEntInfoService;
import com.xy.boot.open.util.ParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 企业资料上报
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class EntInfoController {

    @Autowired
    private IEntInfoService iEntInfoService;

    @PostMapping({"/entInfoUpload"})
    public ReturnDTO entInfoUpload(@RequestBody @Valid EntInfoParam param, BindingResult mesResult) {
        //type=ADD，xyEntCode为空时，提示改为，新增时，xyEntCode要为空
        String errorMsg = ParamUtils.validCodeAndType(param.getXyEntCode(), param.getType(), DataTypeEnum.ENT.getDesc());
        if (!StringUtils.isEmpty(errorMsg)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), errorMsg);
        }
        if (mesResult.hasErrors()) {
            log.info("[已拒收]企业编码:{}, 企业名称: {}, 校验结果:{}",param.getXyEntCode(), param.getEntName(), mesResult.getFieldError().getDefaultMessage());
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        try {
            return iEntInfoService.upload(param);
        } catch (Exception e) {
            log.error("异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }
}