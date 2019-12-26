package com.xy.boot.open.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xy.boot.open.entity.enums.ActionTypeEnum;
import com.xy.boot.open.entity.enums.SubMenuActionTypeEnum;
import com.xy.boot.open.model.params.MenuInfoParam;
import com.xy.boot.open.model.params.SubMenuInfoParam;
import org.springframework.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理菜单工具类
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-08.
 **/
public class MenuUtils {

    /**
     * 检查菜单重复
     *
     * @param param
     * @return
     */
    List<String> names = new ArrayList<>();

    public boolean checkDuplicationName(final MenuInfoParam param) {
        //检查主菜单名称
        if (checkDuplicationName(param.getItemName01()) || checkDuplicationName(param.getItemName02()) || checkDuplicationName(param.getItemName03())) {
            return true;
        }
        //检查子菜单名称
        if (checkDuplicationName(param.getSubMenu01()) || checkDuplicationName(param.getSubMenu02()) || checkDuplicationName(param.getSubMenu03())) {
            return true;
        }
        return false;
    }

    /**
     * 检查菜单重复
     *
     * @param menuName
     * @return true 重复按钮名称
     */
    private boolean checkDuplicationName(String menuName) {
        if (CollectionUtils.isEmpty(names)) {
            names = new ArrayList<>();
        }
        if (names.contains(menuName)) {
            return true;
        }
        names.add(menuName);
        return false;
    }

    /**
     * 检查菜单重复
     *
     * @param subMenus
     * @return true 重复按钮名称
     */
    private boolean checkDuplicationName(List<SubMenuInfoParam> subMenus) {
        if (CollectionUtils.isEmpty(subMenus)) {
            return false;
        }
        for (SubMenuInfoParam submenu : subMenus) {
            if (checkDuplicationName(submenu.getSubItemName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查菜单类型及配置数据,非打开子菜单需有效数据
     *
     * @param itemType
     * @param itemTypeVal
     * @return
     */
    public static boolean checkItemVal(String itemType, String itemTypeVal) {
        if (ActionTypeEnum.MENU.getType().equals(itemType)) {
            return true;
        } else {
            if (org.springframework.util.StringUtils.isEmpty(itemType)) {
                return false;
            }
        }
        SubMenuActionTypeEnum anEnum = SubMenuActionTypeEnum.getEnum(itemType);
        if (anEnum != null) {
            return checkItemValEmpty(itemTypeVal);
        }
        return false;
    }


    /**
     * 判断是否有效值
     *
     * @return
     */
    private static boolean checkItemValEmpty(String itemTypeVal) {
        try {
            JSONObject jsonObject = JSON.parseObject(itemTypeVal);
            for (Map.Entry entry : jsonObject.entrySet()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
