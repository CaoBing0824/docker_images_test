package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.params.RelationInfoParam;
import com.xy.boot.open.service.IMenuRelationInfoService;
import com.xy.boot.open.util.ParamUtils;
import com.xy.boot.open.util.XyAgentCodeHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 菜单关联数据
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class MenuRelationInfoController {

	@Autowired
	private IMenuRelationInfoService service;

	@Autowired
	private XyAgentCodeHolder codeHolder;

	@PostMapping({"/relationInfoUpload"})
	public ReturnDTO relationInfoUpload(@RequestBody @Valid RelationInfoParam param, BindingResult mesResult) {
		//入参日志
		log.info(JSON.toJSONString(param));
		//参数验证
		String errorMsg = ParamUtils.validCodeAndType(param.getXyRelationCode(), param.getType(), DataTypeEnum.MENU_RELATION.getDesc());
		//type=ADD，xyEntCode为空时，提示改为，新增时，xyEntCode要为空
		if (!StringUtils.isEmpty(errorMsg)) {
			return  ReturnDTOUtil.opCheckError(errorMsg);
		}
		if (mesResult.hasErrors()) {
			return ReturnDTOUtil.opCheckError(mesResult);
		}

		//返回对象必须通过 ReturnDTO
		ReturnDTO returnDTO;
		try {
//			代理商编号
			param.setXyAgentCode(codeHolder.getAgentCode());
			returnDTO = service.upload(param);
		} catch (XyException ex) {
			returnDTO = ex.getReturnDTO("保存异常");
		}
		//出参日志
		log.info(JSON.toJSONString(returnDTO));
		return returnDTO;
	}
}