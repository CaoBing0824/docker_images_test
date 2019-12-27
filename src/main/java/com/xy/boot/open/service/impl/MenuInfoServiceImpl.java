package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TDataRelationEdit;
import com.xy.boot.open.entity.TMenuDetailInfo;
import com.xy.boot.open.entity.TMenuInfo;
import com.xy.boot.open.entity.enums.CheckStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.mapper.TOpenApiMenuInfoMapper;
import com.xy.boot.open.model.constvar.MenuConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.MenuInfoParam;
import com.xy.boot.open.model.params.SubMenuInfoParam;
import com.xy.boot.open.service.IDataRelationEditService;
import com.xy.boot.open.service.IMenuDetailInfoService;
import com.xy.boot.open.service.IMenuInfoService;
import com.xy.boot.open.util.XyCodeUtil;
import com.xy.boot.open.util.XyVersionUtil;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Slf4j
@Service
public class MenuInfoServiceImpl extends ServiceImpl<TOpenApiMenuInfoMapper, TMenuInfo> implements IMenuInfoService {
    @Autowired
    private XyCodeUtil xyCodeUtil;

    @Autowired
    private IDataRelationEditService iDataRelationEditService;

    @Autowired
    private IMenuDetailInfoService menuDetailInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO upload(final MenuInfoParam param) {
        String xyMenuCode = param.getXyMenuCode();
        if (OperateTypeEnum.ADD.getType().equals(param.getType())) {
            xyMenuCode = xyCodeUtil.getXyCode(DataTypeEnum.MENU);
        }
        //保存菜单主体信息
        TMenuInfo info = new TMenuInfo();
        BeanUtils.copyProperties(param, info);
        info.setStartTime(!org.springframework.util.StringUtils.isEmpty(param.getStartTime()) ? DateTime.parse(param.getStartTime()).toDate() : null);
        info.setEndTime(!org.springframework.util.StringUtils.isEmpty(param.getEndTime()) ? DateTime.parse(param.getEndTime()).toDate() : null);
        info.setXyCode(xyMenuCode);
        info.setVersion(XyVersionUtil.getXyVersion());
        info.setBrandCode(param.getBrandCode());
        this.insert(info);
        //保存关联关系
        iDataRelationEditService.updateDataRelation(info.getXyCode(), info.getVersion(), DataTypeEnum.MENU, CheckStatusEnum.DATA_STATUS_SUBMIT);
        //保存菜单详情信息
        //获取父菜单
        List<TMenuDetailInfo> list = new ArrayList<>();
        list.add(new TMenuDetailInfo(MenuConstVar.MENU_01_CODE, param.getItemName01(), param.getItemType01(), param.getItemTypeVal01(), info.getXyCode(), info.getVersion()));
        list.add(new TMenuDetailInfo(MenuConstVar.MENU_02_CODE, param.getItemName02(), param.getItemType02(), param.getItemTypeVal02(), info.getXyCode(), info.getVersion()));
        list.add(new TMenuDetailInfo(MenuConstVar.MENU_03_CODE, param.getItemName03(), param.getItemType03(), param.getItemTypeVal03(), info.getXyCode(), info.getVersion()));
        //获取子菜单
        addSubMenuDetail(list, param, info.getXyCode(), info.getVersion());
        menuDetailInfoService.insertBatch(list);
        return ReturnDTOUtil.success(new RespSimpleBody(info.getVersion(), info.getXyCode()));
    }

    @Override
    public String getMenuCodeByPubCode(String pubCode) {
        EntityWrapper<TMenuInfo> tMenuInfoEntityWrapper = new EntityWrapper<>();
        tMenuInfoEntityWrapper.eq(TMenuInfo.TB_XY_PUB_CODE, pubCode)
                .eq(TMenuInfo.TB_STATUS, StatusEnum.VALID);

        TMenuInfo menuInfo = this.selectOne(tMenuInfoEntityWrapper);
        if (menuInfo != null){
            return menuInfo.getXyCode();
        }
        return null;
    }

    /**
     * 增加子菜单
     *
     * @param list
     * @param param
     */
    private void addSubMenuDetail(List<TMenuDetailInfo> list, final MenuInfoParam param, final String xyCode, final String version) {
        addSubMenuDetail(list, param.getSubMenu01(), xyCode, version, MenuConstVar.MENU_01_PREFIX_CODE);
        addSubMenuDetail(list, param.getSubMenu02(), xyCode, version, MenuConstVar.MENU_02_PREFIX_CODE);
        addSubMenuDetail(list, param.getSubMenu03(), xyCode, version, MenuConstVar.MENU_03_PREFIX_CODE);
    }

    /**
     * 增加子菜单
     *
     * @param list
     * @param submenus
     */
    private void addSubMenuDetail(List<TMenuDetailInfo> list, final List<SubMenuInfoParam> submenus, final String xyCode, final String version, final String itemCodePrefix) {
        if (!CollectionUtils.isEmpty(submenus)) {
            for (int i = 0; i < submenus.size(); i++) {
                SubMenuInfoParam info = submenus.get(i);
                list.add(new TMenuDetailInfo(itemCodePrefix + (i + 1), info.getSubItemName(), info.getSubItemType(), info.getSubItemTypeVal(), xyCode, version));
            }
        }

    }


}