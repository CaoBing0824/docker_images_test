package com.xy.boot.open.util;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 地址有效性检测
 *
 * @author masienlin@mfexcel.com
 * @since 2019-05-30
 */
@Slf4j
@Component
public class AddressDetectionUtil {

    @Value("${amap.search-place.url}")
    private String searchUrl ;

    @Value("${amap.key}")
    private String key;

    /**
     * 有效性判断
     * @param address 待判地址
     * @return 判断结果 true:地址有效 false:地址无效
     */
    public boolean validAddress(String address){

        // 常量声明
        final String AMAP_POIS = "pois";
        final String POI_META_NAME = "name";
        final int FIRST = 0;

        // 组装参数发起请求
        Map paramMap = new HashMap<>();
        paramMap.put("key", key);
        paramMap.put("keywords", address);
        paramMap.put("output", "JSON");
        paramMap.put("offset", 5); // pois 有数据，最多返回5条

        String resultStr = HttpClientUtil.post(searchUrl, paramMap);
        log.info("validAddress 请求参数 = {}", paramMap);
        log.info("validAddress 请求结果 = {}", resultStr);

        // 处理请求结果
        JSONObject obj = null;
        JSONArray resultData = null;
        if( resultStr.indexOf( AMAP_POIS ) != -1 ){
            obj = new JSONObject(resultStr);
            resultData = obj.getJSONArray(AMAP_POIS);
        }

        // pois 数组有数据
        if( resultData !=null && !resultData.isNull(FIRST) ) {
            JSONObject poiObj = resultData.getJSONObject( FIRST );
            // 判断地址有效性（是否存在）
            if( !poiObj.isNull(POI_META_NAME) ){
                return true;
            }
        }
        return false;
    }

}
