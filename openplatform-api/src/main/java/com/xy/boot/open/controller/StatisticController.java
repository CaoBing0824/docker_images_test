package com.xy.boot.open.controller;

import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.params.PubStatisticDataParam;
import com.xy.boot.open.service.IStatisticService;
import com.xy.boot.open.service.IYellowInfoService;
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
 * 统计
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class StatisticController {

    @Autowired
    private IStatisticService iStatisticService;

    @PostMapping({"/statistics/pub"})
    public ReturnDTO channelsUpload(@RequestBody @Valid PubStatisticDataParam param, BindingResult mesResult) {
        //参数验证
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        try {
            return iStatisticService.pubStatisticData(param);
        } catch (Exception e) {
            log.error("异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }
}