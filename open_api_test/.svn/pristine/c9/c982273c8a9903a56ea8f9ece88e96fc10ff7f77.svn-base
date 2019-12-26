package com.xy.boot.open.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TMenuInfo;
import com.xy.boot.open.entity.enums.ActionTypeEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.MenuInfoParam;
import com.xy.boot.open.service.IBrandInfoService;
import com.xy.boot.open.service.IMenuInfoService;
import com.xy.boot.open.util.MenuUtils;
import com.xy.boot.open.util.ParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class MenuInfoController {
    @Value("${maximumOfBrandCodes}")
    private  int maximumOfBrandCodes;

    @Value("${maximumOfBrandLength}")
    private  int maximumOfBrandLength;

    @Autowired
    private IMenuInfoService iMenuInfoService;

    @Autowired
    private IBrandInfoService brandInfoService;

    @PostMapping({"/menuInfoUpload"})
    public ReturnDTO channelsUpload(@RequestBody @Valid MenuInfoParam param, BindingResult mesResult) {
        String errorMsg = ParamUtils.validCodeAndType(param.getXyMenuCode(), param.getType(), DataTypeEnum.MENU.getDesc());
        if (!StringUtils.isEmpty(errorMsg)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), errorMsg);
        }
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //验证修改时菜单编号是否在公众号下
        if (OperateTypeEnum.MODIFY.getType().equalsIgnoreCase(param.getType()) ) {
            EntityWrapper<TMenuInfo> ew = new EntityWrapper<>();
            ew.eq(TMenuInfo.TB_XY_PUB_CODE, param.getXyPubCode());
            ew.eq(TMenuInfo.TB_XY_CODE, param.getXyMenuCode());
            Integer count = iMenuInfoService.selectCount(ew);
            if (count == null || count == 0) {
                return ReturnDTOUtil.opCheckError("该菜单编码不存在于该公众号编码下");
            }
        }
        //校验品牌参数
        //个数不能超过配置的个数
        if (!StringUtils.isEmpty(param.getBrandCode())) {
            String[] brands = param.getBrandCode().split(SysConstVar.ENG_REDNIK);
            if (brands.length == 0) {//处理
                param.setBrandCode("");
            }else{
                //去重
                List listTemp = new ArrayList();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < brands.length; i++){
                    if(!listTemp.contains(brands[i])){
                        listTemp.add(brands[i]);
                        sb.append(brands[i]).append(SysConstVar.ENG_REDNIK);
                    }
                }
                param.setBrandCode(sb.toString());
            }
        }

        String isBrandCodeValid = brandInfoService.isBrandCodeValid(param.getBrandCode(),maximumOfBrandCodes,maximumOfBrandLength);
        if(null != isBrandCodeValid){
            return ReturnDTOUtil.custom(HttpCodeEnum.INVALID_REQUEST.getCode(), isBrandCodeValid);
        }
        //判断品牌是否在品牌列表中
        List<String> notExists = brandInfoService.checkBrandExists(param.getBrandCode());
        if(null != notExists){
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "品牌编码"+notExists.toString()+"不存在");
        }

        //有效期开始时间和结束时间判断
        if (!StringUtils.isEmpty(param.getStartTime()) && !StringUtils.isEmpty(param.getEndTime())) {
            if (!DateTime.parse(param.getStartTime()).isBefore(DateTime.parse(param.getEndTime()))) {
                log.warn("数据生效时间须小于失效时间");
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "数据生效时间须小于失效时间");
            }
        }
        //子菜单总数
        int totalSubMenu = 0;
        //检查子菜单个数
        if (ActionTypeEnum.MENU.getType().equalsIgnoreCase(param.getItemType01())) {
            if (CollectionUtils.isEmpty(param.getSubMenu01())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "左菜单类型为MENU,子菜单不能为空");
            }
            if (StringUtils.isEmpty(param.getItemTypeVal01()) || !SysConstVar.MENU_ITEM_TYPE_VAL_EMPTY.equals(param.getItemTypeVal01())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "左菜单类型为MENU,菜单配置值请传{}");
            }
            totalSubMenu += param.getSubMenu01().size();
            if (param.getSubMenu01().size() < SysConstVar.SUB_MENU_MIN_SIZE || param.getSubMenu01().size() > SysConstVar.SUB_MENU_MAX_SIZE) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "左菜单类型为MENU,子菜单个数最少为2最大为4");
            }
        } else {
            if (!CollectionUtils.isEmpty(param.getSubMenu01())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "左菜单类型非MENU,子菜单请为空");
            }
        }
        if (ActionTypeEnum.MENU.getType().equalsIgnoreCase(param.getItemType02())) {
            if (CollectionUtils.isEmpty(param.getSubMenu02())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "中菜单类型为MENU,子菜单不能为空");
            }
            if (StringUtils.isEmpty(param.getItemTypeVal02()) || !SysConstVar.MENU_ITEM_TYPE_VAL_EMPTY.equals(param.getItemTypeVal02())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "中菜单类型为MENU,菜单配置值请传{}");
            }
            totalSubMenu += param.getSubMenu02().size();
            if (param.getSubMenu02().size() < SysConstVar.SUB_MENU_MIN_SIZE || param.getSubMenu02().size() > SysConstVar.SUB_MENU_MAX_SIZE) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "中菜单类型为MENU,子菜单个数最少为2最大为4");
            }
        } else {
            if (!CollectionUtils.isEmpty(param.getSubMenu02())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "中菜单类型非MENU,子菜单请为空");
            }
        }
        if (ActionTypeEnum.MENU.getType().equalsIgnoreCase(param.getItemType03())) {
            if (CollectionUtils.isEmpty(param.getSubMenu03())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "右菜单类型为MENU,子菜单不能为空");
            }
            if (StringUtils.isEmpty(param.getItemTypeVal03()) || !SysConstVar.MENU_ITEM_TYPE_VAL_EMPTY.equals(param.getItemTypeVal03())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "右菜单类型为MENU,菜单配置值请传{}");
            }
            totalSubMenu += param.getSubMenu03().size();
            if (param.getSubMenu03().size() < SysConstVar.SUB_MENU_MIN_SIZE || param.getSubMenu03().size() > SysConstVar.SUB_MENU_MAX_SIZE) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "右菜单类型为MENU,子菜单个数最少为2最大为4");
            }
        } else {
            if (!CollectionUtils.isEmpty(param.getSubMenu03())) {
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "右菜单类型非MENU,子菜单请为空");
            }
        }
        //检查菜单总数
        if (SysConstVar.SUB_MENU_TOTAL_MAX_SIZE < totalSubMenu) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "总菜单个数最多10个");
        }
        //检查菜单名称重复
        if (new MenuUtils().checkDuplicationName(param)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "菜单名称不能重复");
        }
        //检查主菜单的菜单项配置有效性
        if (!MenuUtils.checkItemVal(param.getItemType01(), param.getItemTypeVal01()) ||
                !MenuUtils.checkItemVal(param.getItemType02(), param.getItemTypeVal02()) ||
                !MenuUtils.checkItemVal(param.getItemType03(), param.getItemTypeVal03())) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "菜单类型值需为有效json字符");
        }
        try {
            return iMenuInfoService.upload(param);
        } catch (Exception e) {
            log.error("异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }

}