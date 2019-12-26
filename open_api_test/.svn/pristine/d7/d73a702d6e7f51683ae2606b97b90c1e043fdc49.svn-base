package com.xy.boot.open.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.open.constant.HttpConstant;
import com.xy.boot.open.service.IXySecurityService;
import com.xy.boot.open.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-09 下午 04:16
 */
@Slf4j
@Service
public class XySecurityServiceImpl implements IXySecurityService {

    @Value("${xy-verify.checkTokenUrl}")
    private String checkTokenUrl;

    @Override
    public boolean verifyToken(String token) throws XyException {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        try {
            Map paramsMap = new HashMap();
            paramsMap.put(HttpConstant.TOKEN, token);
            String result = HttpClientUtil.get(checkTokenUrl, paramsMap);
            ReturnDTO dto = JSONObject.parseObject(result, ReturnDTO.class);
            if (dto != null) {
                if (HttpCodeEnum.OK.getCode() == dto.getCode()) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("验证token异常{}", e);
        }
        return false;
    }
}
