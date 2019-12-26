package com.xy.boot.open.controller;

import com.xy.boot.open.util.SensitiveWordUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class sensitiveWordTest extends BaseTest {


    @Autowired
    private SensitiveWordUtil sensitivewordUtil;

    @Test
    public void test() throws Exception {
        String msg = sensitivewordUtil.validSensitiveWord("江青刘广智 空军老毛诚信通手机商城中年美妇a4ytokyohot轮法功人类灭亡进程表");
        log.info("检查结果：{}", msg);
        Set<String> result = sensitivewordUtil.getSensitiveWord("江青刘广智 空军老毛诚信通手机商城中年美妇a4ytokyohot轮法功人类灭亡进程表", SensitiveWordUtil.MAX_MATCH_TYPE);
        log.info(result.toString());
    }



}