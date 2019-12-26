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
 * 黄页信息测试方法
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-09 上午 10:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class YellowInfoControllerTest extends BaseTest {


    @Test
    @Transactional
    public void test() throws Exception {
        String apiUrl = "/yellowPageInfoUpload";
        ReturnDTO returnDTO;
        String xyCode;
        String xyPubCode = getXyPubCode();
        log.info("数据异常");
        returnDTO = this.post(apiUrl, "{}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("数据新增");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyPubCode\": \"" + xyPubCode + "\",\n" +
                "  \"xyYellowCode\": \"\",\n" +
                "  \"slogan\": \"10086\",\n" +
                "  \"introduction\": \"44\",\n" +
                "  \"tellPhone\": \"tellPhone\",\n" +
                "  \"address\": \"address\",\n" +
                "  \"serchKey\": \"serchKey\",\n" +
                "  \"openTime\": \"openTime\",\n" +
                "  \"range\": \"range\",\n" +
                "  \"lonlat\": \"116.494531;39.958308\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
        xyCode = TestResultUtils.conver(returnDTO).getXyCode();
        log.info("数据修改");
        returnDTO = this.post(apiUrl, "{\n" +
                "  \"xyPubCode\": \"" + xyPubCode + "\",\n" +
                "  \"xyYellowCode\": \"" + xyCode + "\",\n" +
                "  \"slogan\": \"10086\",\n" +
                "  \"introduction\": \"44\",\n" +
                "  \"tellPhone\": \"tellPhone\",\n" +
                "  \"address\": \"address\",\n" +
                "  \"serchKey\": \"serchKey\",\n" +
                "  \"openTime\": \"openTime\",\n" +
                "  \"range\": \"range\",\n" +
                "  \"lonlat\": \"116.494531;39.958308\",\n" +
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"MODIFY\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));


        log.info("slogan必填 为空报错");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"请致电123456\",\n" +
                "\t\"address\": \"广东省，珠海市13356544号。\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));


        log.info("slogan必填 企业口号不能超过100个字符 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"请致电123456\",\n" +
                "\t\"address\": \"广东省，珠海市13356544号。\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("tellPhone必填 为空 服务热线不能为空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"\",\n" +
                "\t\"address\": \"广东省，珠海市13356544号。\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));


        log.info("tellPhone必填 服务热线不能超过20个字符 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"123456789012345678901\",\n" +
                "\t\"address\": \"广东省，珠海市13356544号。\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("address必填 空 地址不能为空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"123456789012345678901\",\n" +
                "\t\"address\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("address必填 地址长度大于100 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("introduction必填 空 企业介绍不能为空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("introduction必填 空 企业介绍不能为空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("introduction必填 长度大于1000 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("serchKey非必填 长度大于1000 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.95\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"dsd\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("lonlat必填 经纬度不能为空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("lonlat必填 经纬度不能为空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("lonlat必填 经纬度不能超过40个字符 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.494;39.958888888895888888889588888888\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
        log.info("lonlat必填 经纬度非法 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.4912a4;39.958888888895888\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"我们的营业时间是12:00到半夜，欢迎!\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("openTime必填 空 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.49124;39.958888888895888\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("openTime必填 长度大于100 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"rangerangerange\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.49124;39.958888888895888\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("range必填 空 经营范围不能为空");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.49124;39.958888888895888\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

        log.info("range必填 长度大于1000 错误");
        returnDTO = this.post(apiUrl, "{\n" +
                "\t\"tellPhone\": \"12345678901234567890\",\n" +
                "\t\"address\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"xyPubCode\": \"PUB400984581399945216\",\n" +
                "\t\"range\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" +
                "\t\"type\": \"ADD\",\n" +
                "\t\"lonlat\": \"116.49124;39.958888888895888\",\n" +
                "\t\"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "\t\"serchKey\": \"111\",\n" +
                "\t\"openTime\": \"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\",\n" +
                "\t\"slogan\": \"1234567890\",\n" +
                "\t\"introduction\": \"最大的酒吧！\",\n" +
                "\t\"xyYellowCode\": \"\"\n" +
                "}");
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));



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
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
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
                "  \"extMap\": \"{\\\"2\\\":\\\"\\\"}\",\n" +
                "  \"type\": \"ADD\"\n" +
                "}");
        return TestResultUtils.conver(returnDTO).getXyCode();
    }
}