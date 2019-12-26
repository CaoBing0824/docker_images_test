package com.xy.boot.open.controller;

import com.xy.basic.common.utility.JsonUtil;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.ChannelInfoParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通道号信息测试方法
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-09 上午 10:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NumInfoControllerTest extends BaseTest {



    @Test
    @Transactional//注释后才可测试多数据源
    public void channelsUpload01() throws Exception {
        String apiUrl = "/channelsUpload";
        ReturnDTO returnDTO = null;
        ChannelInfoParam param = new ChannelInfoParam();
        param.setXyPubCode("PUB400984581399945216");
        //param.setReceiveNum("073185585686");106967382***121
        //param.setReceiveNum("0731855856444");
        param.setReceiveNum("0731855856444");
        param.setType("ADD");
        param.setExtMap("{'1':'1'}");
        param.setAreas("86");
        param.setNumExclusive("");
        param.setSignatures("阿妮甜甜迈思专卖店");
        String   paramJson = JsonUtil.simpleObject2Json(param);
        System.out.print(paramJson);
        returnDTO = this.post(apiUrl, paramJson);
        System.out.print("结果返回："+returnDTO.getCode());

    }

    @Test
    @Transactional
    public void test() throws Exception {
        String apiUrl = "/channelsUpload";
        ReturnDTO returnDTO;
        String xyCode;
        String xyPubCode = getXyPubCode();
        log.info("数据异常");
        returnDTO = this.post(apiUrl, "{}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("数据新增,正常");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"12345123451234***\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
        xyCode = TestResultUtils.conver(returnDTO).getXyCode();
        log.info("数据修改");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyPubCode\": \"" + xyPubCode + "\",\n" +
                "  \"xyChannelCode\": \"" + xyCode + "\",\n" +
                "  \"receiveNum\": \"10086\",\n" +
                "  \"areas\": \"44\",\n" +
                "  \"signatures\": \"签名\",\n" +
                "  \"purpose\": \"目的\",\n" +
                "  \"extMap\": \"{'1':'1'}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("receiveNum必填 ,为空时 报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("receiveNum必填 ,单个长度 >20 报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1234512345123451234***\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("receiveNum必填 ,单个长度 <=20 正常");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1234512345123451234*\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));


        log.info("receiveNum必填 ,1069***~1069*********** 有效值");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1234512345123451234*\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));


        log.info("receiveNum必填 ,1069***~1069*********** 号段大于20");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1069***~1069*****************\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));


        log.info("receiveNum必填 ,1069***************** 号码大于20");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1069***~1069*****************\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));


        log.info("receiveNum必填 ,非数字和星号 ,字母,报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1069a\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("receiveNum必填 ,非数字和星号 ,中文,报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"11\",\n" +
                "\t\"receiveNum\": \"1069哈哈\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("areas必填 为空报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));


        log.info("areas必填 86全国互斥");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"86;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("areas必填 多个合法数据");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("signatures必填 为空报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("signatures必填 为空报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("signatures必填 signatures 大于40报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下午好2569下午好2569下午好2569下午好25下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("signatures必填 个数 大于10报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下;午;好;2569;下;午;好;2569;下;午;好;2569下午好25下午好2569下午好2569下午好2569下午好25\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("signatures必填 单个签名长度大于40报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44;11\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下;午;好;2569;下;午;好;2569;下;午;好;2569下午好25下午好2569下午好2569下午好2569下午好252569下午好2569下午好2569\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("signatures必填 正常 去重");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下;午;好;2569;下;午;好;2569下午好25下午好2569下午好25695\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("purpos非必填 为空正常");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下;午\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

        log.info("purpos非必填 大于40");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"12345678901234567890123456789012345678901\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下;午\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("purpos非必填 小于等于40");
        returnDTO = this.post(apiUrl, "{\n" +
                "   \"extMap\": \"{'1':'1'}\",\n" +
                "\t\"xyChannelCode\": \"\",\n" +
                "\t\"purpose\": \"1234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"areas\": \"44\",\n" +
                "\t\"receiveNum\": \"10691231231\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"signatures\": \"下;午\"\n" +
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
                "  \"extMap\": \"\",\n" +
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