package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.util.result.RespSimpleBody;

/**
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-08.
 **/
public class TestResultUtils {

    public static RespSimpleBody conver(ReturnDTO dto) {
        Object message = dto.getMessage();
        if (message == null) {
            return null;
        }
        if (message instanceof JSONObject) {
            return JSON.parseObject(message.toString(), RespSimpleBody.class);
        }
        return null;
    }

}
