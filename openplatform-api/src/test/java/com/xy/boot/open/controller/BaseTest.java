package com.xy.boot.open.controller;


import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    @Autowired
    private WebApplicationContext context;

    public MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void contextLoads() {
    }

    public ReturnDTO get(String url, String paramVo) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)//指定接口
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);//指定接口格式
        //带参
        if (paramVo != null) {
            builder = builder.content(paramVo);
        }

        //获取接口返回内容
        String contentResult = mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())//判定状态
                .andDo(MockMvcResultHandlers.print()) //打印结果
                .andExpect(MockMvcResultMatchers.content().string(Matchers.notNullValue()))//接口不为空
                .andReturn().getResponse().getContentAsString();//获取接口参数
        //通过JSON转义成JAVA对象
        ReturnDTO returnDTO = JSON.parseObject(contentResult, ReturnDTO.class);
        return returnDTO;
    }

    public ReturnDTO post(String url, String paramVo) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)//指定接口
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);//指定接口格式
        //带参
        if (paramVo != null) {
            builder = builder.content(paramVo);
        }
        builder.header("appId", "08608256");
        //获取接口返回内容
        String contentResult = mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())//判定状态
                .andDo(MockMvcResultHandlers.print()) //打印结果
                .andExpect(MockMvcResultMatchers.content().string(Matchers.notNullValue()))//接口不为空
                .andReturn().getResponse().getContentAsString();//获取接口参数
        //通过JSON转义成JAVA对象
        ReturnDTO returnDTO = JSON.parseObject(contentResult, ReturnDTO.class);
        return returnDTO;
    }


    @After
    public void setDown() {
        mvc = null;
    }

}
