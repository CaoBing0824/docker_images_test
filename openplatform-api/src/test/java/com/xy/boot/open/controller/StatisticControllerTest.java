package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.PubStatisticDataParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticControllerTest extends BaseTest {

    @Test
    @Transactional
    public void getPubStatisticData() throws Exception {

        PubStatisticDataParam param = new PubStatisticDataParam();
        param.setXyPubCodes("PUB420635996820869120;PUB420972494367756288");
        param.setDate("2019-02-17");

        ReturnDTO returnDTO = this.post("/statistics/pub", JSON.toJSONString(param));
        Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
    }
}
