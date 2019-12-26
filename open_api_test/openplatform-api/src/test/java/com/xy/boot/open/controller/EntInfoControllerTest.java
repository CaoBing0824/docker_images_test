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
 * 企业信息测试方法
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-09 上午 10:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntInfoControllerTest extends BaseTest {


    @Test
    @Transactional
    public void entInfoUpload01() throws Exception {
        String apiUrl = "/entInfoUpload";
        ReturnDTO returnDTO = null;
        log.info("数据新增");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
    }

    @Test
    @Transactional
    public void test() throws Exception {
        String apiUrl = "/entInfoUpload";
        ReturnDTO returnDTO = null;
        String xyCode = "";
        log.info("数据异常");
        returnDTO = this.post(apiUrl, "{}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("数据新增");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("数据修改");
        xyCode = TestResultUtils.conver(returnDTO).getXyCode();
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
        log.info("企业名称为空");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("企业名称大于100");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entNameentNameentNamentNameentNameentNamentNameentNameentNamentNameentNameentNamentNameentNameentNam1\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("类型为 ADD,xyEntCode须为空 ");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entNameentNameentNamentNameentNameentNamentNameentNameentNamentNameentNameentNamentNameentNameentNam1\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("类型为 MODIFY,xyEntCode不能为空 ");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("creditCode（必填） 长度=17 返回提示信息，企业统一信用注册码必须为18个字符");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"12345678901234567\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("creditCode（必填） 不能为空");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("creditCode（必填） 长度=19 返回提示信息，企业统一信用注册码必须为18个字符");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"1234567890123456789\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("creditCode（必填） 非英文和字母 返回提示信息，企业统一信用注册码只能由数字或字母组成");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345哈哈9\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("authDocx（非必填）空");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345678\",\n" +
                "  \"authDocx\": \"\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
        log.info("authDocx（非必填）空 不为 jpg png pdf");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyCode + "\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345678\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg1\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
    }
}