package com.xy.boot.open.interceptor;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.constant.HttpConstant;
import com.xy.boot.open.entity.TAgentExtInfo;
import com.xy.boot.open.entity.enums.StatusEnum;
import com.xy.boot.open.service.IAgentExtInfoService;
import com.xy.boot.open.service.impl.XySecurityServiceImpl;
import com.xy.boot.open.util.XyAgentCodeHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Value("${xy-verify.open}")
    private int verifyToken;

    @Autowired
    private XyAgentCodeHolder xyAgentCodeHolder;

    @Autowired
    private IAgentExtInfoService iAgentExtInfoService;

    @Autowired
    private XySecurityServiceImpl xySecurityService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //验证AppId
        String appId = httpServletRequest.getHeader(HttpConstant.APPID);
        if (org.springframework.util.StringUtils.isEmpty(appId)) {
            log.error("appId 为空");
            httpServletResponse.setStatus(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode());
            return false;
        }
        if (appId != null) {
            EntityWrapper<TAgentExtInfo> gentExtInfo = new EntityWrapper<>();
            gentExtInfo.eq(TAgentExtInfo.TB_APP_ID, appId);
            TAgentExtInfo extInfo = iAgentExtInfoService.selectOne(gentExtInfo);
            if (extInfo != null) {
                String xyCode = extInfo.getXyCode();
                xyAgentCodeHolder.setAgentCode(xyCode);
            } else {
                httpServletResponse.setStatus(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode());
                return false;
            }
        }
        //验证token
        if (StatusEnum.VALID.getStatusCode() == verifyToken) {
            String token = httpServletRequest.getHeader(HttpConstant.TOKEN);
            if (StringUtils.isEmpty(token)) {
                log.error("token 为空");
                httpServletResponse.setStatus(HttpCodeEnum.UNAUTHORIZED.getCode());
            }
            if (xySecurityService.verifyToken(httpServletRequest.getHeader("token"))) {
                return true;
            } else {
                log.error("token 无效");
                httpServletResponse.setStatus(HttpCodeEnum.UNAUTHORIZED.getCode());
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        xyAgentCodeHolder.clear();
    }
}
