package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.model.params.UpdateStatusParam;
import com.xy.boot.open.service.IUpOffLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 上下线接口
 */
@Slf4j
@RestController
@RequestMapping("/updateStatus")
public class OnOffLineController {

    @Autowired
    private IUpOffLineService onOffLineService;

    @PostMapping(value = "/offline")
    public ReturnDTO offLine(@RequestBody @Valid UpdateStatusParam param, BindingResult mesResult){
        //入参日志
        log.info(JSON.toJSONString(param));
        //参数验证
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //返回对象必须通过 ReturnDTO
        ReturnDTO returnDTO;
        try {
            returnDTO = onOffLineService.offLine(param);
        } catch (XyException ex) {
            returnDTO = ex.getReturnDTO("保存异常");
        }
        //出参日志
        log.info(JSON.toJSONString(returnDTO));
        return returnDTO;
    }

    @PostMapping(value = "/upline")
    public ReturnDTO online(@RequestBody @Valid UpdateStatusParam param, BindingResult mesResult){
        //入参日志
        log.info(JSON.toJSONString(param));
        //参数验证
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //返回对象必须通过 ReturnDTO
        ReturnDTO returnDTO;
        try {
            returnDTO = onOffLineService.onLine(param);
        } catch (XyException ex) {
            returnDTO = ex.getReturnDTO("保存异常");
        }
        //出参日志
        log.info(JSON.toJSONString(returnDTO));
        return returnDTO;
    }
}
