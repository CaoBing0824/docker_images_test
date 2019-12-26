package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TDataRelationHistory;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.mapper.TOpenApiDataRelationHistoryMapper;
import com.xy.boot.open.service.IDataRelationHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 数据关系信息
 *
 * @author heyuancheng@mfexcel.com
 * Date:2018-12-29.
 **/
@Slf4j
@Service
public class DataRelationHistoryServiceImpl extends ServiceImpl<TOpenApiDataRelationHistoryMapper, TDataRelationHistory> implements IDataRelationHistoryService {
	@Override
	public TDataRelationHistory getData(final String xyCode, final CheckStatusEnum status, final DataTypeEnum dataType) {
		EntityWrapper<TDataRelationHistory> qryWrapper = new EntityWrapper<>();
		qryWrapper.eq(TDataRelationHistory.TB_XY_CODE, xyCode);
		qryWrapper.eq(TDataRelationHistory.TB_CHECK_STATUS, status);
		qryWrapper.eq(TDataRelationHistory.TB_DATA_TYPE, dataType);
		qryWrapper.orderDesc(Arrays.asList(TDataRelationHistory.TB_UPDATED));
		return this.selectOne(qryWrapper);
	}

	@Override
	public boolean checkXyCode(final String xyCode, final DataTypeEnum dataType) {
		if (StringUtils.isEmpty(xyCode)) {
			return false;
		}
		EntityWrapper<TDataRelationHistory> qryWrapper = new EntityWrapper<>();
		qryWrapper.eq(TDataRelationHistory.TB_XY_CODE, xyCode);
		if (dataType != null && dataType != DataTypeEnum.DEFAULT) {
			qryWrapper.eq(TDataRelationHistory.TB_DATA_TYPE, dataType);
		}
		TDataRelationHistory data = this.selectOne(qryWrapper);
		if (null == data) {
			return false;
		}
		return true;
	}
}
