package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.model.params.CheckInfoQueryParam;
import com.xy.boot.open.service.ICheckInfoQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 查询审核状态接口
 */
@Slf4j
@RestController
public class CheckInfoQueryController {

    @Autowired
    private ICheckInfoQueryService ICheckInfoQueryService;

    @PostMapping(value = "/checkInfoQuery")
    public ReturnDTO checkInfoQuery(@RequestBody @Valid CheckInfoQueryParam param, BindingResult mesResult){
        //入参日志
        log.info(JSON.toJSONString(param));
        //参数验证
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //返回对象必须通过 ReturnDTO
        ReturnDTO returnDTO;
        try {
            returnDTO = ICheckInfoQueryService.getCheckInfoQueryStatus(param);
        } catch (XyException ex) {
            returnDTO = ex.getReturnDTO("保存异常");
        }
        //出参日志
        log.info(JSON.toJSONString(returnDTO));
        return returnDTO;
    }
}
