package com.xy.boot.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.*;
import com.xy.boot.open.entity.enums.ActionTypeEnum;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.TOpenApiActionInfoMapper;
import com.xy.boot.open.mapper.TOpenApiDataRelationEditMapper;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.ActionInfoParam;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xy.boot.open.service.IActionInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class ActionInfoServiceImpl extends ServiceImpl<TOpenApiActionInfoMapper, TActionInfo> implements IActionInfoService {

	@Autowired
	private TOpenApiDataRelationEditMapper relationEditMapper;

	@Autowired
	private XyCodeUtil codeUtil;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ReturnDTO actionInfoUpload(ActionInfoParam param) {
		ReturnDTO dto;
		String type = param.getType();

		if (type.equalsIgnoreCase(OperateTypeEnum.ADD.getType())) {
			dto = add(param);
		} else if (type.equalsIgnoreCase(OperateTypeEnum.MODIFY.getType())) {
			dto = modify(param);
		} else if (type.equalsIgnoreCase(OperateTypeEnum.CANCEL.getType())) {
			dto = cancel(param);
		} else {
			dto =  ReturnDTOUtil.opCheckError("操作类型无效");
		}
		return dto;
	}

	private ReturnDTO cancel(ActionInfoParam param) {
		return  ReturnDTOUtil.opCheckError("操作类型无效");
	}

	private ReturnDTO modify(ActionInfoParam param) {
		return add(param);
	}

	private ReturnDTO add(ActionInfoParam param) {
		RespSimpleBody body = insertRelationInfo(param);
		ReturnDTO dto = ReturnDTOUtil.success();
		dto.setMessage(body);
		return dto;
	}

	private RespSimpleBody insertRelationInfo(ActionInfoParam param) {
		String version = param.getVersion();
		if (StringUtils.isEmpty(version)) {
			version = XyVersionUtil.getXyVersion();
		}

		//		更新version即可,否则为插入新数据
		boolean isUpdateReleaseEdit = true;
		String xyCode = param.getXyBtnCode();
		if (StringUtils.isEmpty(xyCode)) {
			xyCode = codeUtil.getXyCode(DataTypeEnum.BUBBLE_BTN);
			isUpdateReleaseEdit = false;
		}
		Date date = new Date();
		//		TODO 用户名
		String username = "Ray test";

		//		封装明细数据
		TActionInfo actionInfo = new TActionInfo();
		actionInfo.setXyPubCode(param.getXyPubCode());
		actionInfo.setSceneCode(param.getSceneCode());
		actionInfo.setBtnName(param.getBtnName());
		//动作类型
		actionInfo.setActionType(ActionTypeEnum.getEnum(param.getActionType()));
		actionInfo.setPriority(Integer.parseInt(param.getPriority()));
		actionInfo.setActionTypeVal(param.getActionTypeVal());
		//品牌代码
		actionInfo.setBrandCode(param.getBrandCode());
		if (!StringUtils.isEmpty(param.getStartTime())) {
			actionInfo.setStartTime(DateTime.parse(param.getStartTime()).toDate());
		}
		if (!StringUtils.isEmpty(param.getEndTime())) {
			actionInfo.setEndTime(DateTime.parse(param.getEndTime()).toDate());
		}

		actionInfo.setXyCode(xyCode);
		actionInfo.setVersion(version);
		actionInfo.setExtMap(param.getExtMap());
		actionInfo.setStatus(StatusEnum.VALID);
		actionInfo.setCreated(date);
		actionInfo.setUpdated(date);
		actionInfo.setCreatedBy(username);
		actionInfo.setUpdatedBy(username);

		actionInfo.setXySmsCode(param.getXySmsCode());

		boolean isSuccess = insert(actionInfo);
		if (!isSuccess) {
			log.error("插入数据失败");
			log.error(JSON.toJSONString(param));
			throw new XyException("插入数据失败");
		}

//		更新编辑表
//		TODO 工单id
		long workorderId = -1L;

//		插入编辑表数据
		if (isUpdateReleaseEdit) {
			relationEditMapper.updateDataRelation(
					xyCode, version, DataTypeEnum.BUBBLE_BTN.getType(),
					workorderId, CheckStatusEnum.DATA_STATUS_SUBMIT.getStatusCode(),
					StatusEnum.VALID.getStatusCode());
		} else {
//		获取编辑表数据
			TDataRelationEdit releaseEdit = new TDataRelationEdit();
			releaseEdit.setXyCode(xyCode);
			releaseEdit.setVersion(version);
			releaseEdit.setDataType(DataTypeEnum.BUBBLE_BTN);
			releaseEdit.setWorkorderId(workorderId);
			releaseEdit.setCheckStatus(CheckStatusEnum.DATA_STATUS_SUBMIT);
			releaseEdit.setStatus(StatusEnum.VALID);
			releaseEdit.setCreated(date);
			releaseEdit.setUpdated(date);
			releaseEdit.setCreatedBy(username);
			releaseEdit.setUpdatedBy(username);

			Integer releaseInserStatus = relationEditMapper.insert(releaseEdit);
			if (releaseInserStatus == null || releaseInserStatus < 0) {
				log.error("插入数据失败");
				log.error(JSON.toJSONString(param));
				throw new XyException("插入数据失败");
			}
		}

		return new RespSimpleBody(version, xyCode);
	}
}