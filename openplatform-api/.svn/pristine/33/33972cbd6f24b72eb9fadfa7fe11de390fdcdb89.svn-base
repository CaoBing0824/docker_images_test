package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.CheckInfoQueryParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OnOffLineControllerTest extends BaseTest{

    @Test
    @Transactional
    public void offLine() throws Exception{
        CheckInfoQueryParam param = new CheckInfoQueryParam();
        param.setXyCode("WHITE_LIST398796176546798465");
        //post执行 返回成ReturnDTO对象
        ReturnDTO returnDTO =this.post("/updateStatus/offline", JSON.toJSONString(param));
        Assert.assertTrue(returnDTO.getMessage() != null);
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
    }

    @Test
    @Transactional
    public void upLine() throws Exception{
        CheckInfoQueryParam param = new CheckInfoQueryParam();
        param.setXyCode("WHITE_LIST398796177497133056");
        //post执行 返回成ReturnDTO对象
        ReturnDTO returnDTO =this.post("/updateStatus/upline", JSON.toJSONString(param));
        Assert.assertTrue(returnDTO.getMessage() != null);
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
    }
}
