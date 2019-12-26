package com.xy.boot.open.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一逻辑处理
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-05.
 **/
@Slf4j
@Aspect
@Component
public class HttpAspect {

    @Pointcut("execution(public * com.xy.boot.open.controller..*.*(..))")
    public void cut() {
    }

    @Before("cut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("RemoteAddr = {}", request.getRemoteAddr());
        log.info("接口url = {}", request.getRequestURI());
        log.info("接口参数 = {}", joinPoint.getArgs());
    }

    @After("cut()")
    public void doAfter() {
    }

    @AfterReturning(returning = "obj", pointcut = "cut()")
    public void doAfterReturning(Object obj) {
        log.info("接口响应 = {}", JSON.toJSONString(obj));
    }

}
