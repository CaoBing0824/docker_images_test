package com.xy.boot.open.interceptor;

import com.alibaba.fastjson.JSON;
import com.xy.boot.open.util.MyAESutils;
import com.xy.boot.open.util.XyAgentCodeHolder;
import com.xy.boot.open.util.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;

/**
 * 消息体返回加密/压缩
 * Created by fuwenshen
 * Date:2019/2/19
 * Time:14:16
 */

@Slf4j
@Component
//@ControllerAdvice(basePackages = "com.xy.boot.open.controller")
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {


    @Autowired
    private XyAgentCodeHolder xyAgentCodeHolder;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if(body!=null){
            String message = JSON.toJSONString(body);
            log.info("加密之前的消息体："+message);
            try {
                //todo  test
                String appSecret = xyAgentCodeHolder.getXyAgentAppSecret();
                byte[] messageBt = ZipUtils.compress(message.getBytes("UTF-8"));
                messageBt = MyAESutils.encrypt(messageBt,appSecret);

                return new String(messageBt,"UTF-8");
            } catch (IOException e) {
                log.error("beforeBodyWrite 处理Response异常！");
                e.printStackTrace();
            }finally {
                xyAgentCodeHolder.clearAppSecret();
            }
        }

        return body;
    }
}
