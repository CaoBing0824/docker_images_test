package com.xy.boot.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TDataRelationRelease;
import com.xy.boot.open.entity.enums.ApiAreaEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.model.bo.*;
import com.xy.boot.open.model.params.PubStatisticDataParam;
import com.xy.boot.open.service.IDataRelationHistoryService;
import com.xy.boot.open.service.IMenuInfoService;
import com.xy.boot.open.service.IPubExtInfoService;
import com.xy.boot.open.service.IStatisticService;
import com.xy.boot.open.util.PageAndGetData;
import com.xy.boot.open.util.result.RespSimpleBody;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据统计
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-02-25.
 **/
@Slf4j
@Service
public class StatisticServiceImpl implements IStatisticService {

    @Value("${getPubData}")
    private String getPubData;

    @Autowired
    private IDataRelationHistoryService iDataRelationHistoryService;

    @Autowired
    private IPubExtInfoService pubExtInfoService;

    @Autowired
    private IMenuInfoService menuInfoService;

    @Override
    public ReturnDTO pubStatisticData(final PubStatisticDataParam param) {
        String xyPubCodes = param.getXyPubCodes();
        String[] xyCodeArr = xyPubCodes.split(";");
        List<StatisticPubDataBO> list = new ArrayList<>();
        for (String xyCode : xyCodeArr) {
            if (!iDataRelationHistoryService.checkXyCode(xyCode, DataTypeEnum.PUB)) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "公众号编码无效：" + xyCode);
            }
            Map<String,Object> params = new HashMap<>();

            String pubId = pubExtInfoService.getPubIdByPubCode(xyCode);
            if (pubId == null){
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "公众号编码无效：" + xyCode);
            }
            params.put("pubId",pubId);
            //校验日期格式有效性
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try{
                formatter.setLenient(false);
                Date date = formatter.parse(param.getDate());
                params.put("date",param.getDate());
            }catch(Exception e){
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "日期格式无效：" + param.getDate());
            }
            JSONObject pubData = PageAndGetData.getInterfaceData(getPubData, params, "message");

            IStatisticPubDataBO IStatisticPubDataBO = JSON.parseObject(pubData.toString(), IStatisticPubDataBO.class);

            StatisticPubDataBO resultPubData = new StatisticPubDataBO();
            //pubId
            resultPubData.setXyPubCode(xyCode);
            resultPubData.setSmsParseCount(IStatisticPubDataBO.getSmsParseCount());
            List<StatisticMenuDetailDataBO> menuDatas = new ArrayList<>();
            StatisticMenuDetailDataBO menu = new StatisticMenuDetailDataBO();
            //MenuCode
            String menuCode = menuInfoService.getMenuCodeByPubCode(xyCode);
            if (menuCode != null){
                menu.setMenuCode(menuCode);
            }
            menu.setMenuShowCount(IStatisticPubDataBO.getMenuShowCount());

            /**
             * 菜单点击详情
             */
            List<MenuCountDetailsDataBO> menuClickDetail = new ArrayList<>();
            /**
             * 菜单区域点击详情
             */
            List<MenuAreaCountDetailsDataBO> menuAreaClickCountDetail = new ArrayList<>();

            for (MenuCountDetailsDataBO menuCountDetailsDataBO : IStatisticPubDataBO.getMenuClickDetail()){
                MenuCountDetailsDataBO click = new MenuCountDetailsDataBO();
                click.setMenuName(menuCountDetailsDataBO.getMenuName());
                click.setCount(menuCountDetailsDataBO.getCount());
                menuClickDetail.add(click);
            }

            for (IMenuAreaCountDetailsDataBO menuAreaCountDetailsDataBO : IStatisticPubDataBO.getMenuAreaClickCountDetail()) {
                MenuAreaCountDetailsDataBO areaclick = new MenuAreaCountDetailsDataBO();
                areaclick.setMenuName(menuAreaCountDetailsDataBO.getMenuName());
                areaclick.setCount(menuAreaCountDetailsDataBO.getCount());
                areaclick.setArea(ApiAreaEnum.getCodeByDesc(menuAreaCountDetailsDataBO.getProvince()));
                menuAreaClickCountDetail.add(areaclick);
            }

            menu.setMenuClickCountDetail(menuClickDetail);
            menu.setMenuAreaClickCountDetail(menuAreaClickCountDetail);

            menuDatas.add(menu);
            resultPubData.setMenuDatas(menuDatas);

            /**
             * 按钮展现数
             */
            List<BtnCountDetailsDataBO> btnShowCountDetail = new ArrayList<>();
            /**
             * 按钮点击数
             */
            List<BtnCountDetailsDataBO> btnClickCountDetail = new ArrayList<>();
            /**
             * 按钮点击数
             */
            List<BtnAreaCountDetailsDataBO> btnClickAreaCountDetail = new ArrayList<>();

            for (BtnCountDetailsDataBO btnCountDetailsDataBO : IStatisticPubDataBO.getBtnShowCountDetail()) {
                BtnCountDetailsDataBO show = new BtnCountDetailsDataBO();
                show.setButtonName(btnCountDetailsDataBO.getButtonName());
                show.setCount(btnCountDetailsDataBO.getCount());
                show.setSceneCode(btnCountDetailsDataBO.getSceneCode());
                btnShowCountDetail.add(show);
            }

            for (BtnCountDetailsDataBO btnCountDetailsDataBO : IStatisticPubDataBO.getBtnClickCountDetail()) {
                BtnCountDetailsDataBO btnclick = new BtnCountDetailsDataBO();
                btnclick.setButtonName(btnCountDetailsDataBO.getButtonName());
                btnclick.setCount(btnCountDetailsDataBO.getCount());
                btnclick.setSceneCode(btnCountDetailsDataBO.getSceneCode());
                btnClickCountDetail.add(btnclick);
            }

            for (IBtnAreaCountDetailsDataBO btnAreaCountDetailsDataBO : IStatisticPubDataBO.getBtnClickAreaCountDetail()) {
                BtnAreaCountDetailsDataBO btnareaclick = new BtnAreaCountDetailsDataBO();
                btnareaclick.setButtonName(btnAreaCountDetailsDataBO.getButtonName());
                btnareaclick.setCount(btnAreaCountDetailsDataBO.getCount());
                btnareaclick.setSceneCode(btnAreaCountDetailsDataBO.getSceneCode());
                btnareaclick.setArea(ApiAreaEnum.getCodeByDesc(btnAreaCountDetailsDataBO.getProvince()));
                btnClickAreaCountDetail.add(btnareaclick);
            }

            resultPubData.setBtnShowCountDetail(btnShowCountDetail);
            resultPubData.setBtnClickCountDetail(btnClickCountDetail);
            resultPubData.setBtnClickAreaCountDetail(btnClickAreaCountDetail);
            list.add(resultPubData);
        }
        return ReturnDTOUtil.success(list);
    }
}
