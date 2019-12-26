package com.xy.boot.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.google.common.base.Joiner;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.entity.*;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.*;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.WhiteInfoParam;
import com.xy.boot.open.util.GenIDUtil;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xy.boot.open.service.IWhiteInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class WhiteInfoServiceImpl extends ServiceImpl<TOpenApiWhiteInfoMapper, TWhiteInfo> implements IWhiteInfoService {

	@Autowired
	private TOpenApiDataRelationReleaseMapper releaseMapper;

	@Autowired
	private TOpenApiDataRelationEditMapper relationEditMapper;

	@Autowired
	private TOpenApiMuiltDataMapper muiltDataMapper;

	@Autowired
	private TOpenApiDataRelationHistoryMapper historyMapper;

	@Autowired
	private TOpenApiPubInfoMapper pubInfoMapper;

	@Autowired
	private TOpenApiEntInfoMapper entInfoMapper;

	@Autowired
	private XyCodeUtil codeUtil;

	@Autowired
	private GenIDUtil idUtil;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ReturnDTO whiteInfoUpload(WhiteInfoParam param) {
//		获取代理商所拥有的企业
		EntityWrapper<TEntInfo> entEW = new EntityWrapper<>();
		entEW.setSqlSelect(TEntInfo.TB_XY_CODE);
		entEW.eq(TEntInfo.TB_XY_AGENT_CODE, param.getXyAgentCode());
		List<TEntInfo> entLs = entInfoMapper.selectList(entEW);
		List<String> entCodeLs = entLs.parallelStream().map(x -> {
			return x.getXyCode();
		}).collect(Collectors.toList());

//		通过企业获取所拥有的pub
		EntityWrapper<TPubInfo> pubEW = new EntityWrapper<>();
		pubEW.in(TPubInfo.TB_XY_ENT_CODE, entCodeLs);
		List<TPubInfo> pubLs = pubInfoMapper.selectList(pubEW);
		List<String> pubCodeLs = pubLs.parallelStream().map(x -> {
			return x.getXyCode();
		}).collect(Collectors.toList());

		List<String> xyPubCodeLs = Arrays.asList(param.getXyPubCodes().split(SysConstVar.ENG_REDNIK));
		for (String xyPubCode : xyPubCodeLs) {
			if (!pubCodeLs.contains(xyPubCode)) {
				return ReturnDTOUtil.opCheckError("公众号：" + xyPubCode + "不存在");
			}
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


	private ReturnDTO add(WhiteInfoParam param) {
//		新增 根据企业 判断是否重复
		EntityWrapper<TDataRelationEdit> relationEditEW = new EntityWrapper<>();
		EntityWrapper<TDataRelationRelease> relationReleaseEW = new EntityWrapper<>();

		relationEditEW.eq(TDataRelationEdit.TB_DATA_TYPE, DataTypeEnum.WHITE_LIST);
		relationReleaseEW.eq(TDataRelationRelease.TB_DATA_TYPE, DataTypeEnum.WHITE_LIST);

		List<TDataRelationEdit> relationEditsLs = relationEditMapper.selectList(relationEditEW);
		List<TDataRelationRelease> relationReleaseLs = releaseMapper.selectList(relationReleaseEW);

		if (CollectionUtils.isEmpty(relationEditsLs) && CollectionUtils.isEmpty(relationReleaseLs)) {
//			无旧的imei，无需判断 直接入库
//			执行更新
			RespSimpleBody body = insertWhiteList(param);
			ReturnDTO dto = ReturnDTOUtil.success();
			dto.setMessage(body);
			return dto;
		}

//		查询出获取编辑表中 xycode和version，用于查询明细表
		List<String> codeVer = relationEditsLs.parallelStream().map(x -> {
			return x.getXyCode() + x.getVersion();
		}).collect(Collectors.toList());

//		查询出获取上线表中 xycode和version，用于查询明细表
//		合并编辑表和上线表存在的Code、Ver，作为key
		codeVer.addAll(relationReleaseLs.parallelStream().map(x -> {
			return x.getXyCode() + x.getVersion();
		}).collect(Collectors.toList()));

		String agentCode = param.getXyAgentCode();
		EntityWrapper<TWhiteInfo> whiteInfoEW = new EntityWrapper<>();
		whiteInfoEW.eq(TWhiteInfo.TB_XY_AGENT_CODE, agentCode);
		List<TWhiteInfo> whiteInfoLs = selectList(whiteInfoEW);

		//过滤明细表，在编辑表和上线表中不存在的Code、Ver
		whiteInfoLs.parallelStream().filter(x -> {
			return codeVer.contains(x.getXyCode() + x.getVersion());
		}).collect(Collectors.toList());

//		得到查询多值信息表的条件imeiID（relation_id）
		List<Long> relationIds = whiteInfoLs.parallelStream().map(x -> {
			return x.getImeisRelationId();
		}).collect(Collectors.toList());

		EntityWrapper<TMuiltData> muiltDataEW = new EntityWrapper<>();
		muiltDataEW.in(TMuiltData.TB_RELATION_ID, relationIds);
		List<TMuiltData> muiltDataLs = muiltDataMapper.selectList(muiltDataEW);
//		当前imei
		List<String> oldImeis = muiltDataLs.parallelStream().map(x -> {
			return x.getValue();
		}).collect(Collectors.toList());

//		传入的imei
		List<String> imeis = Arrays.asList(param.getImeis().split(SysConstVar.ENG_REDNIK));

		List<String> intersection = imeis.parallelStream().filter(x -> oldImeis.contains(x)).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(intersection)) {
			return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(),
					"设备：" + intersection + "已上报，请勿重复上报: ");
		}
//		入库
		RespSimpleBody body = insertWhiteList(param);
		ReturnDTO dto = ReturnDTOUtil.success();
		dto.setMessage(body);
		return dto;
	}


	private ReturnDTO modify(WhiteInfoParam param) {

		EntityWrapper<TDataRelationHistory> hisEW = new EntityWrapper<>();
		hisEW.eq(TDataRelationHistory.TB_DATA_TYPE, DataTypeEnum.WHITE_LIST);
		hisEW.eq(TDataRelationHistory.TB_XY_CODE, param.getXyWhiteCode());
		List<TDataRelationHistory> hisLs = historyMapper.selectList(hisEW);
		if (CollectionUtils.isEmpty(hisLs)) {
			return ReturnDTOUtil.custom(HttpCodeEnum.NOT_FOUND.getCode(), "无法找到该xyWhiteCode" + param.getXyWhiteCode());
		}

//		存在审核通过的状态
		TDataRelationHistory his = hisLs.parallelStream().filter(x -> {
			return x.getCheckStatus() == CheckStatusEnum.DATA_STATUS_OK;
		}).findFirst().orElse(null);

//		审核通过后（即对该设备操作过一次审核通过），不支持更新品牌、机型和设备码；若更新其他信息，则提示：不支持更新品牌、机型和设备码，若需更新设备，请新增设备
		if (his != null) {
			EntityWrapper<TWhiteInfo> whiteInfoEW = new EntityWrapper<>();
			whiteInfoEW.eq(TWhiteInfo.TB_XY_CODE, his.getXyCode());
			whiteInfoEW.eq(TWhiteInfo.TB_VERSION, his.getVersion());
			TWhiteInfo whiteInfoLs = selectOne(whiteInfoEW);
			if (whiteInfoLs == null) {
				return ReturnDTOUtil.custom(HttpCodeEnum.NOT_FOUND.getCode(), "无法找到该xyWhiteCode" + param.getXyWhiteCode());
			}

			EntityWrapper<TMuiltData> muiltDataEW = new EntityWrapper<>();
			muiltDataEW.eq(TMuiltData.TB_RELATION_ID, whiteInfoLs.getImeisRelationId());
			List<TMuiltData> muiltDataLs = muiltDataMapper.selectList(muiltDataEW);
			List<String> oldImeiLs = muiltDataLs.parallelStream().map(x -> {
				return x.getValue();
			}).collect(Collectors.toList());
			String oldImeis = Joiner.on(SysConstVar.ENG_REDNIK).join(oldImeiLs);
			if (!oldImeis.equalsIgnoreCase(param.getImeis()) ||
					!whiteInfoLs.getPhoneBrand().equalsIgnoreCase(param.getPhoneBrand()) ||
					!whiteInfoLs.getPhoneType().equalsIgnoreCase(param.getPhoneType())) {
				return ReturnDTOUtil.custom(HttpCodeEnum.NOT_FOUND.getCode(), "不支持更新品牌、机型和设备码，若需更新设备，请新增设备");
			}
		}
//		执行更新
		RespSimpleBody body = insertWhiteList(param);
		ReturnDTO dto = ReturnDTOUtil.success();
		dto.setMessage(body);
		return dto;
	}

	private ReturnDTO cancel(WhiteInfoParam param) {
		return ReturnDTOUtil.opCheckError("操作类型无效");
	}

	/**
	 * 插入数据
	 *
	 * @param param
	 * @return
	 */
	private RespSimpleBody insertWhiteList(WhiteInfoParam param) {
//		准备入库
		long imeiRelationId = idUtil.nextId();
		long pubCodeIdRelationId = idUtil.nextId();
		String version = param.getVersion();
		if (StringUtils.isEmpty(version)) {
			version = XyVersionUtil.getXyVersion();
		}

		String xyCode = param.getXyWhiteCode();
//		更新version即可,否则为插入新数据
		boolean isUpdateReleaseEdit = true;
		if (StringUtils.isEmpty(xyCode)) {
			xyCode = codeUtil.getXyCode(DataTypeEnum.WHITE_LIST);
			isUpdateReleaseEdit = false;
		}

		Date date = new Date();
//		TODO 用户名
		String username = "Ray Test";

//		封装明细数据
		TWhiteInfo whiteInfo = new TWhiteInfo();
		whiteInfo.setXyAgentCode(param.getXyAgentCode());
		whiteInfo.setPhoneBrand(param.getPhoneBrand());
		whiteInfo.setPhoneType(param.getPhoneType());
		whiteInfo.setImeisRelationId(imeiRelationId);
		whiteInfo.setXyPubCodesRelationId(pubCodeIdRelationId);
		whiteInfo.setExtMap(param.getExtMap());
		whiteInfo.setVersion(version);
		whiteInfo.setXyCode(xyCode);
		whiteInfo.setStatus(StatusEnum.VALID);
		whiteInfo.setCreated(date);
		whiteInfo.setUpdated(date);
		whiteInfo.setCreatedBy(username);
		whiteInfo.setUpdatedBy(username);

		boolean isSuccess = insert(whiteInfo);
		if (!isSuccess) {
			log.error("插入数据失败");
			log.error(JSON.toJSONString(param));
			throw new XyException("插入数据失败");
		}

//		TODO 工单id
		long workorderId = -1L;

//		插入编辑表数据
		if (isUpdateReleaseEdit) {
			relationEditMapper.updateDataRelation(
					xyCode, version, DataTypeEnum.WHITE_LIST.getType(),
					workorderId, CheckStatusEnum.DATA_STATUS_SUBMIT.getStatusCode(),
					StatusEnum.VALID.getStatusCode());
		} else {
			//		获取编辑表数据
			TDataRelationEdit releaseEdit = new TDataRelationEdit();
			releaseEdit.setXyCode(xyCode);
			releaseEdit.setVersion(version);
			releaseEdit.setDataType(DataTypeEnum.WHITE_LIST);
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
//		分解imei数据\分解pubid数据
		List<TMuiltData> tMuiltDatas = param.getImeiLs().parallelStream().map(x -> {
			TMuiltData tMuiltDataImei = new TMuiltData();
			tMuiltDataImei.setRelationId(imeiRelationId);
			tMuiltDataImei.setValue(x);
			tMuiltDataImei.setStatus(StatusEnum.VALID);
			tMuiltDataImei.setCreated(date);
			tMuiltDataImei.setUpdated(date);
			tMuiltDataImei.setCreatedBy(username);
			tMuiltDataImei.setUpdatedBy(username);
			return tMuiltDataImei;
		}).collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(param.getXyPubCodeLs())) {
			tMuiltDatas.addAll(param.getXyPubCodeLs().parallelStream().map(x -> {
				TMuiltData tMuiltDataPubId = new TMuiltData();
				tMuiltDataPubId.setRelationId(pubCodeIdRelationId);
				tMuiltDataPubId.setValue(x);
				tMuiltDataPubId.setStatus(StatusEnum.VALID);
				tMuiltDataPubId.setCreated(date);
				tMuiltDataPubId.setUpdated(date);
				tMuiltDataPubId.setCreatedBy(username);
				tMuiltDataPubId.setUpdatedBy(username);
				return tMuiltDataPubId;
			}).collect(Collectors.toList()));

//		批量入库 多值信息表
			muiltDataMapper.insertBatch(tMuiltDatas);
		}

		return new RespSimpleBody(version, xyCode);
	}
}