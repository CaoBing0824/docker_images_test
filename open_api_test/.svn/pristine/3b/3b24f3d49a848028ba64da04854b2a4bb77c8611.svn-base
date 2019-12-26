package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.params.WhiteInfoParam;
import com.xy.boot.open.service.IWhiteInfoService;
import com.xy.boot.open.util.ParamUtils;
import com.xy.boot.open.util.XyAgentCodeHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class WhiteInfoController {

	@Autowired
	private IWhiteInfoService service;

	@Autowired
	private XyAgentCodeHolder codeHolder;

	@PostMapping({"/whiteInfoUpload"})
	public ReturnDTO whiteInfoUpload(@RequestBody @Valid WhiteInfoParam param, BindingResult mesResult) {
//		入参日志
		log.info(JSON.toJSONString(param));
//		参数验证
		String errorMsg = ParamUtils.validCodeAndType(param.getXyWhiteCode(), param.getType(),DataTypeEnum.WHITE_LIST.getDesc());
		if (!StringUtils.isEmpty(errorMsg)) {
			return ReturnDTOUtil.opCheckError(errorMsg);
		}
		if (mesResult.hasErrors()) {
			return ReturnDTOUtil.opCheckError(mesResult);
		}
//		分解imei数据
		if(CollectionUtils.isEmpty(param.getImeiLs())){
			return ReturnDTOUtil.opCheckError("设备码不能为空");
		}

		if(CollectionUtils.isEmpty(param.getXyPubCodeLs())){
			return ReturnDTOUtil.opCheckError("公众号不能为空");
		}

		if (param.getXyPubCodeLs().size() > 100) {
			return ReturnDTOUtil.opCheckError("公众号不能大于100个");
		}

		//返回对象必须通过 ReturnDTO
		ReturnDTO returnDTO;
		try {
			param.setXyAgentCode(codeHolder.getAgentCode());
			returnDTO = service.whiteInfoUpload(param);
		} catch (XyException ex) {
			returnDTO = ex.getReturnDTO("保存异常");
		}
		//出参日志
		log.info(JSON.toJSONString(returnDTO));
		return returnDTO;
	}
}