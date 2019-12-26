package com.xy.boot.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.*;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.*;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.RelationInfoParam;
import com.xy.boot.open.util.GenIDUtil;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xy.boot.open.service.IMenuRelationInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单关联数据
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class MenuRelationInfoServiceImpl extends ServiceImpl<TOpenApiMenuRelationInfoMapper, TMenuRelationInfo> implements IMenuRelationInfoService {

	@Autowired
	private XyCodeUtil codeUtil;

	@Autowired
	private GenIDUtil idUtil;

	@Autowired
	private TOpenApiMuiltDataMapper muiltDataMapper;

	@Autowired
	private TOpenApiDataRelationEditMapper relationEditMapper;

	@Autowired
	private TOpenApiNumInfoMapper numInfoMapper;

	@Autowired
	private TOpenApiMenuInfoMapper menuInfoMapper;

	@Autowired
	private TOpenApiPubInfoMapper pubInfoMapper;


	@Override
	@Transactional(rollbackFor = Exception.class)
	public ReturnDTO upload(RelationInfoParam param) {

		EntityWrapper<TPubInfo> pubEW = new EntityWrapper<>();
		pubEW.eq(TPubInfo.TB_XY_CODE, param.getXyPubCode());
		pubEW.last(" limit 1 ");
		List<TPubInfo> pubLs = pubInfoMapper.selectList(pubEW);
		if(CollectionUtils.isEmpty(pubLs)){
			return ReturnDTOUtil.opCheckError("公众号编码无效");
		}

//		判断公众号对应的号码编码
		if(!StringUtils.isEmpty(param.getXyNumCode())){
			EntityWrapper<TNumInfo> numInfoEW = new EntityWrapper<>();
			numInfoEW.eq(TNumInfo.TB_XY_PUB_CODE, param.getXyPubCode());
			numInfoEW.eq(TNumInfo.TB_XY_CODE, param.getXyNumCode());
			List<TNumInfo> numLs = numInfoMapper.selectList(numInfoEW);
			if (CollectionUtils.isEmpty(numLs)) {
				return ReturnDTOUtil.opCheckError("号码编码不属于公众号 " + pubLs.get(0).getPubName());
			}
		}

//		判断公众号对应的菜单编码
		EntityWrapper<TMenuInfo> menuInfoEW = new EntityWrapper<>();
		menuInfoEW.eq(TMenuInfo.TB_XY_PUB_CODE, param.getXyPubCode());
		menuInfoEW.eq(TMenuInfo.TB_XY_CODE,param.getXyMenuCode());
		List<TMenuInfo> menuLs = menuInfoMapper.selectList(menuInfoEW);
		if (CollectionUtils.isEmpty(menuLs)) {
			return ReturnDTOUtil.opCheckError("菜单编码不属于公众号 " + pubLs.get(0).getPubName());
		}

		ReturnDTO dto;
		String type = param.getType();
		if (type.equalsIgnoreCase(OperateTypeEnum.ADD.getType())) {
			dto = add(param);
		} else if (type.equalsIgnoreCase(OperateTypeEnum.MODIFY.getType())) {
			dto = modify(param);
		} else if (type.equalsIgnoreCase(OperateTypeEnum.CANCEL.getType())) {
			dto = cancel(param);
		} else {
			dto = ReturnDTOUtil.opCheckError("操作类型无效");
		}
		return dto;
	}

	private ReturnDTO add(RelationInfoParam param) {
		RespSimpleBody body = insertRelationInfo(param);
		ReturnDTO dto = ReturnDTOUtil.success();
		dto.setMessage(body);
		return dto;
	}

	private ReturnDTO modify(RelationInfoParam param) {
		return add(param);
	}

	private ReturnDTO cancel(RelationInfoParam param) {
		return ReturnDTOUtil.opCheckError("操作类型无效");
	}

	private RespSimpleBody insertRelationInfo(RelationInfoParam param) {
		String version = param.getVersion();
		if (StringUtils.isEmpty(version)) {
			version = XyVersionUtil.getXyVersion();
		}

//		更新version即可,否则为插入新数据
		boolean isUpdateReleaseEdit = true;
		String xyCode = param.getXyRelationCode();
		if (StringUtils.isEmpty(xyCode)) {
			xyCode = codeUtil.getXyCode(DataTypeEnum.MENU_RELATION);
			isUpdateReleaseEdit = false;
		}
		long areasRelationId = idUtil.nextId();
		Date date = new Date();
//		TODO 用户名
		String username = "Ray test";

//		封装明细数据
		TMenuRelationInfo relationInfo = new TMenuRelationInfo();
		relationInfo.setXyAgentCode(param.getXyAgentCode());

		relationInfo.setXyPubCode(param.getXyPubCode());
		relationInfo.setXyMenuCode(param.getXyMenuCode());
		relationInfo.setXyNumCode(param.getXyNumCode());

		relationInfo.setAreasRelationId(areasRelationId);
		relationInfo.setExtMap(param.getExtMap());
		relationInfo.setVersion(version);
		relationInfo.setXyCode(xyCode);
		relationInfo.setStatus(StatusEnum.VALID);
		relationInfo.setCreated(date);
		relationInfo.setUpdated(date);
		relationInfo.setCreatedBy(username);
		relationInfo.setUpdatedBy(username);
		relationInfo.setAgPhoneid(param.getAgPhoneId());

		boolean isSuccess = insert(relationInfo);
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
					xyCode, version, DataTypeEnum.MENU_RELATION.getType(),
					workorderId, CheckStatusEnum.DATA_STATUS_SUBMIT.getStatusCode(),
					StatusEnum.VALID.getStatusCode());
		} else {
//		获取编辑表数据
			TDataRelationEdit releaseEdit = new TDataRelationEdit();
			releaseEdit.setXyCode(xyCode);
			releaseEdit.setVersion(version);
			releaseEdit.setDataType(DataTypeEnum.MENU_RELATION);
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

//		分解areas数据
		if(!CollectionUtils.isEmpty(param.getAreasLs())){
			List<TMuiltData> tMuiltDatas = param.getAreasLs().parallelStream().map(x -> {
				TMuiltData tMuiltDataImei = new TMuiltData();
				tMuiltDataImei.setRelationId(areasRelationId);
				tMuiltDataImei.setValue(x);
				tMuiltDataImei.setStatus(StatusEnum.VALID);
				tMuiltDataImei.setCreated(date);
				tMuiltDataImei.setUpdated(date);
				tMuiltDataImei.setCreatedBy(username);
				tMuiltDataImei.setUpdatedBy(username);
				return tMuiltDataImei;
			}).collect(Collectors.toList());

//		批量入库 多值信息表
			muiltDataMapper.insertBatch(tMuiltDatas);
		}
		return new RespSimpleBody(version, xyCode);
	}
}