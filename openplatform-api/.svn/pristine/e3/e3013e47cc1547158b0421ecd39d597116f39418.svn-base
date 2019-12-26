package com.xy.boot.open.controller;

import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公众号信息测试方法
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-09 上午 10:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuInfoControllerTest extends BaseTest {

    @Test
    @Transactional
    public void menuInfoUpload01() throws Exception {
        String apiUrl = "/menuInfoUpload";
        ReturnDTO returnDTO = null;
        log.info("数据新增");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"xyPubCode\": \"PUB401450090432094208\",\n" +
                "\t\"xyMenuCode\": \"\",\n" +
                "\t\"startTime\": \"2019-03-23T06:59:55Z\",\n" +
                "\t\"endTime\": \"2019-03-23T07:59:55Z\",\n" +
                "\t\"extMap\": \"{'1':'1'}\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"brandCode\": \"10000002;10000001;;;;\",\n" +
                "\t\"itemName01\": \"左菜单\",\n" +
                "\t\"itemType01\": \"OPEN_FAST\",\n" +
                "\t\"itemTypeVal01\": \"{\\\"1\\\":\\\"1\\\"}\",\n" +
                "\t\"itemName02\": \"中菜单\",\n" +
                "\t\"itemType02\": \"MENU\",\n" +
                "\t\"itemTypeVal02\": \"{}\",\n" +
                "\t\"subMenu02\": [{\n" +
                "\t\t\"subItemName\": \"中菜单一\",\n" +
                "\t\t\"subItemType\": \"OPEN_URL\",\n" +
                "\t\t\"subItemTypeVal\": \"{\\\"1\\\":\\\"1\\\"}\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"subItemName\": \"中菜单二\",\n" +
                "\t\t\"subItemType\": \"OPEN_URL\",\n" +
                "\t\t\"subItemTypeVal\": \"{\\\"1\\\":\\\"1\\\"}\"\n" +
                "\t}],\n" +
                "\t\"itemName03\": \"右菜单\",\n" +
                "\t\"itemType03\": \"MENU\",\n" +
                "\t\"itemTypeVal03\": \"{}\",\n" +
                "\t\"subMenu03\": [{\n" +
                "\t\t\"subItemName\": \"右菜单一\",\n" +
                "\t\t\"subItemType\": \"OPEN_URL\",\n" +
                "\t\t\"subItemTypeVal\": \"{\\\"1\\\":\\\"1\\\"}\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"subItemName\": \"右菜单二\",\n" +
                "\t\t\"subItemType\": \"OPEN_URL\",\n" +
                "\t\t\"subItemTypeVal\": \"{\\\"1\\\":\\\"1\\\"}\"\n" +
                "\t}]\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
    }


    private String getXyPubCode() throws Exception {
        ReturnDTO returnDTO = this.post("/pubInfoUpload", "{\n" +
                "  \"xyEntCode\": \"" + getXyEntCode() + "\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"中国移动公众号2\",\n" +
                "  \"showLogoWhite\": \"http://qq.jpg\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        return TestResultUtils.conver(returnDTO).getXyCode();
    }

    private String getXyEntCode() throws Exception {
        ReturnDTO returnDTO = this.post("/entInfoUpload", "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        return TestResultUtils.conver(returnDTO).getXyCode();
    }


}