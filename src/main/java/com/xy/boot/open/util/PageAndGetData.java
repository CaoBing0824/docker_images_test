package com.xy.boot.open.util;

import com.xy.basic.common.utility.HttpClientUtil;
import com.xy.basic.common.utility.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by admin on 2018/3/9.
 */
@Slf4j
public class PageAndGetData {

    /**
     * 调用接口返回json对象
     *
     * @param url   接口地址
     * @param param 需要获取的参数
     * @return
     */
    public static JSONObject getInterfaceData(String url, Object param, String data) {

        String paramJson = "";

        if(param == null){
            paramJson = "{}";
        } else {
            paramJson = JsonUtil.simpleObject2Json(param);
        }
        System.out.println("json转换的值：" + paramJson);
        String result = HttpClientUtil.doPost(url, paramJson, "UTF-8");
        System.out.println("result：" + result);

        //将外部接口返回值中body内容转换成json
        JSONObject obj = null;
        JSONArray resultData = null;
        if(result.indexOf(data) != -1){
            obj = new JSONObject(result);
            resultData = obj.getJSONArray(data);
        }
        return resultData.getJSONObject(0);
    }
}
