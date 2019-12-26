package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.SmsParseInfoParam;
import com.xy.boot.open.service.ISmsInfoService;
import com.xy.boot.open.util.ParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 短信卡片上报
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class SmsInfoController {

	@Autowired
	private ISmsInfoService service;

	@PostMapping({"/smsParseInfoUpload"})
	public ReturnDTO smsParseInfoUpload(@RequestBody @Valid SmsParseInfoParam param, BindingResult mesResult) {
		//入参日志
		log.info(JSON.toJSONString(param));
		if(OperateTypeEnum.getDataTypeEnumByType(param.getType()) != null && param.getType().equals(OperateTypeEnum.MODIFY.getType()) ){
			return ReturnDTOUtil.opCheckError("当前不支持更新操作");
		}
		//参数验证
		String errorMsg = ParamUtils.validCodeAndType(param.getXySmsCode(), param.getType(), DataTypeEnum.BUBBLE.getDesc());
		if (!StringUtils.isEmpty(errorMsg)) {
			return ReturnDTOUtil.opCheckError(errorMsg);
		}
		if (mesResult.hasErrors()) {
			return ReturnDTOUtil.opCheckError(mesResult);
		}
		List<String> ls =  Arrays.asList(param.getParseContent().split(SysConstVar.ENG_REDNIK));
		if ( ls.size() > 10) {
			return ReturnDTOUtil.opCheckError("期望字段提取不能大于10个");
		}

		//返回对象必须通过 ReturnDTO
		ReturnDTO returnDTO;
		try {
			returnDTO = service.upload(param);
		} catch (XyException ex) {
			returnDTO = ex.getReturnDTO("保存异常");
		}
		//出参日志
		log.info(JSON.toJSONString(returnDTO));
		return returnDTO;
	}

}