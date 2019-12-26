package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TYellowInfo;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.mapper.TOpenApiYellowInfoMapper;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.YellowInfoParam;
import com.xy.boot.open.service.IDataRelationEditService;
import com.xy.boot.open.service.IYellowInfoService;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 黄页信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class YellowInfoServiceImpl extends ServiceImpl<TOpenApiYellowInfoMapper, TYellowInfo> implements IYellowInfoService {
    @Autowired
    private XyCodeUtil xyCodeUtil;

    @Autowired
    private IDataRelationEditService iDataRelationEditService;

    @Override
    public ReturnDTO upload(final YellowInfoParam param) {
        String xyYellowCode = param.getXyYellowCode();
        if (OperateTypeEnum.ADD.getType().equals(param.getType())) {
            //新增
            xyYellowCode = xyCodeUtil.getXyCode(DataTypeEnum.YELLOW_PAGE);
        }
        TYellowInfo info = new TYellowInfo();
        BeanUtils.copyProperties(param, info);
        info.setXyCode(xyYellowCode);
        info.setVersion(XyVersionUtil.getXyVersion());
        this.insert(info);
        iDataRelationEditService.updateDataRelation(info.getXyCode(), info.getVersion(), DataTypeEnum.YELLOW_PAGE, CheckStatusEnum.DATA_STATUS_SUBMIT);
        return ReturnDTOUtil.success(new RespSimpleBody(info.getVersion(), info.getXyCode()));
    }
}