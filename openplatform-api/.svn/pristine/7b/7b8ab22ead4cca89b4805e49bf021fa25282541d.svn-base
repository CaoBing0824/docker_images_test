package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TDataRelationEdit;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.mapper.TOpenApiDataRelationEditMapper;
import com.xy.boot.open.service.IDataRelationEditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * 数据关系信息
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2018-12-29.
 **/
@Slf4j
@Service
public class DataRelationEditServiceImpl extends ServiceImpl<TOpenApiDataRelationEditMapper, TDataRelationEdit> implements IDataRelationEditService {

    @Override
    public TDataRelationEdit getByXyCode(final String xyCode) {
        if (StringUtils.isEmpty(xyCode)) {
            return null;
        }
        EntityWrapper<TDataRelationEdit> qryWrapper = new EntityWrapper<>();
        qryWrapper.eq(TDataRelationEdit.TB_XY_CODE, xyCode);
        return this.selectOne(qryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = REQUIRED)
    public void updateDataRelation(final String xyCode, final String version, final DataTypeEnum dataType, final CheckStatusEnum checkStatus) {
        TDataRelationEdit byXyCode = getByXyCode(xyCode);
        if (byXyCode == null) {
            TDataRelationEdit edit = new TDataRelationEdit();
            edit.setXyCode(xyCode);
            edit.setVersion(version);
            edit.setDataType(dataType);
            this.insert(edit);
        } else {
            byXyCode.setCheckStatus(checkStatus);
            byXyCode.setVersion(version);
            this.updateById(byXyCode);
        }
    }

}
