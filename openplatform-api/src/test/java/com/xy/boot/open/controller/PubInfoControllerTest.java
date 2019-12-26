package com.xy.boot.open.controller;

import com.xy.basic.common.utility.JsonUtil;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.*;
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
public class PubInfoControllerTest extends BaseTest {

    @Test
    @Transactional
    public void testXy() throws Exception {
        String apiUrl = "/pubInfoRenewContract";

        ReturnDTO returnDTO = null;
        PubInfoRenewalContractParam param = new PubInfoRenewalContractParam();
        //param.setXyPubCode("PUB483591773780705280");//01
        //param.setNewExpiryDate("2019-10-23");
        param.setXyPubCode("PUB483591773780705280");
        param.setNewExpiryDate("2019-10-23");
        String   paramJson = JsonUtil.simpleObject2Json(param);
        System.out.print(paramJson);
        returnDTO = this.post(apiUrl, paramJson);
        System.out.print("结果返回："+returnDTO.getCode());
    }

    @Test
    @Transactional
    public void testXyConfirm() throws Exception {
        String apiUrl = "/renewContractNotice";

        ReturnDTO returnDTO = null;
        PubInfoRenewalQueryParam param = new PubInfoRenewalQueryParam();
        //param.setXyPubCode("PUB483591773780705280;PUB400689469830725632;PUB399577333012418560399577333012418560399577333012418560399577333012418560399577333012418560399577333012418560;;;");
        //param.setXyPubCode("PUB483591773780705280;PUB400689469830725632;PUB3995773330124");
        param.setXyPubCode("PUB483591773780705280;PUB400689469830725632;PUB3995773330124");
        //param.setXyPubCode(";;PUB483591773780705280;;");
        String   paramJson = JsonUtil.simpleObject2Json(param);
        System.out.print(paramJson);
        returnDTO = this.post(apiUrl, paramJson);
        System.out.print("结果返回："+returnDTO.getCode());
    }

    @Test
    @Transactional
    public void pubInfoUpload001() throws Exception {
        String apiUrl = "/pubInfoUpload";
        ReturnDTO returnDTO = null;
        String xyCode = "PUB399577333012418560";
        String xyEntCode = "ENT397765091086319616";
        log.info("数据新增");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyEntCode + "\",\n" +
                "  \"xyPubCode\": \"" + xyCode + "\",\n" +
                "  \"pubName\": \"中国移动公众号2222333\",\n" +
                "  \"cooperativeMonth\": \"11\",\n" +
                "  \"showLogoWhite\": \"http://qq.jpg\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
        xyCode = TestResultUtils.conver(returnDTO).getXyCode();
        System.out.println("xyCode:"+xyCode);
    }

    @Test
    @Transactional
    public void test() throws Exception {
        String apiUrl = "/pubInfoUpload";
        ReturnDTO returnDTO = null;
        String xyCode;
        String xyEntCode = getXyEntCode();
        log.info("数据异常");
        returnDTO = this.post(apiUrl, "{}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("数据新增");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyEntCode + "\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"中国移动公众号2\",\n" +
                "  \"showLogoWhite\": \"http://qq.jpg\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
        xyCode = TestResultUtils.conver(returnDTO).getXyCode();

        log.info("数据修改");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyEntCode + "\",\n" +
                "  \"xyPubCode\": \"" + xyCode + "\",\n" +
                "  \"pubName\": \"中国移动公众号2\",\n" +
                "  \"showLogoWhite\": \"http://qq.jpg\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("pubName 非空 ，空，错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"" + xyEntCode + "\",\n" +
                "  \"xyPubCode\": \"" + xyCode + "\",\n" +
                "  \"pubName\": \"\",\n" +
                "  \"showLogoWhite\": \"http://qq.jpg\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("pubName 长度大于40，错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"12345678901234567890123456789012345678901\",\n" +
                "  \"showLogoWhite\": \"http://qq.png\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("pubName 长度等于40，正常");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"1234567890123456789012345678901234567890\",\n" +
                "  \"showLogoWhite\": \"http://qq.png\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("showLogoWhite showLogoColor非必填");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"1234567890123456789012345678901234567890\",\n" +
                "  \"showLogoWhite\": \"\",\n" +
                "  \"showLogoColor\": \"\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("showLogoWhite showLogoWhite 白色后缀错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"1234567890123456789012345678901234567890\",\n" +
                "  \"showLogoWhite\": \"http://qq.png1\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("showLogoWhite showLogoWhite 彩色后缀错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"1234567890123456789012345678901234567890\",\n" +
                "  \"showLogoWhite\": \"http://qq.png\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg1\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("productType（必填） 产品类型不能为空");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"1234567890123456789012345678901234567890\",\n" +
                "  \"showLogoWhite\": \"http://qq.png\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("productType（必填） 产品类型错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyEntCode\": \"ENT397765091086319616\",\n" +
                "  \"xyPubCode\": \"\",\n" +
                "  \"pubName\": \"1234567890123456789012345678901234567890\",\n" +
                "  \"showLogoWhite\": \"http://qq.png\",\n" +
                "  \"showLogoColor\": \"http://qq.jpg\",\n" +
                "  \"startTime\": \"\",\n" +
                "  \"endTime\": \"\",\n" +
                "  \"productType\": \"PP1\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

    }


    private String getXyEntCode() throws Exception {
        ReturnDTO returnDTO = this.post("/entInfoUpload", "{\n" +
                "  \"xyEntCode\": \"\",\n" +
                "  \"entName\": \"entName\",\n" +
                "  \"creditCode\": \"123456789012345679\",\n" +
                "  \"authDocx\": \"https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg\",\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        return TestResultUtils.conver(returnDTO).getXyCode();
    }
}