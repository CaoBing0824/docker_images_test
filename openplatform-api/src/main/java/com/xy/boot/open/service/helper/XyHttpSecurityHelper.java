package com.xy.boot.open.service.helper;

import com.xy.boot.open.constant.BaseConstant;
import com.xy.boot.open.constant.HttpConstant;
import com.xy.boot.open.util.EncryptUtil;
import com.xy.boot.common.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.*;

/**
 * Created by fuwenshen
 * Date:2018/11/1
 * Time:11:33
 */
public class XyHttpSecurityHelper {

    /**
     * 生成通用请求头
     *
     * @param contentType
     * @param Accept
     * @return
     */
    public static HttpHeaders getSafeHttpHeaders(MediaType contentType, String Accept, String token) {
        // 设置请求头信息.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        headers.add(HttpConstant.ACCEPT, Accept);
        headers.add(HttpConstant.TOKEN, token);
        headers.add(HttpConstant.V, "0001");
        headers.add(HttpConstant.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        return headers;
    }


    /**
     * 通过请求头和参数生成 keyValueA
     *
     * @param headers
     * @param paramMaps
     * @return
     */
    public static String getkeyValuesA(HttpHeaders headers, TreeMap paramMaps) {

        paramMaps.put(HttpConstant.TOKEN, headers.getFirst(HttpConstant.TOKEN));
        paramMaps.put(HttpConstant.V, headers.getFirst(HttpConstant.V));
        paramMaps.put(HttpConstant.TIMESTAMP, headers.getFirst(HttpConstant.TIMESTAMP));

        String keyValueA = getkeyValuesA(paramMaps);
        return keyValueA;
    }


    /**
     * 通过通过字典排序参数生成 keyValueA
     *
     * @param paramMaps
     * @return
     */
    public static String getkeyValuesA(TreeMap paramMaps) {
        StringBuffer keyValueA = new StringBuffer();
        Set<Map.Entry<String, Object>> entrySet = paramMaps.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            Object value = entry.getValue();
            if (value == null || value instanceof Collection) {
                continue;
            }
            if (value instanceof String && StringUtils.isEmpty((String) value)) {
                continue;
            }
            keyValueA.append(entry.getKey().trim()).append(BaseConstant.EQUALS).append(value.toString().trim()).append(BaseConstant.COMBINE);
        }
        //删除最后一个&
        keyValueA.deleteCharAt(keyValueA.length() - 1);
        return keyValueA.toString();
    }


    /**
     * 通过 keyValueA 和 密钥 获取签名
     *
     * @param keyValueA
     * @return
     */
    public static String getSignValue(String keyValueA, String appSecret) {

        String keyValueB = getkeyValuesB(keyValueA,appSecret,10);
        String signValue = EncryptUtil.string2SHA256StrJava(keyValueB);
        return signValue;
    }


    /**
     * 获取keyValueB 的值
     * @param keyValueA
     * @param appSecret
     * @param length
     * @return
     */
    public static String getkeyValuesB(String keyValueA, String appSecret,Integer length) {

        if(length!=null && appSecret.length()>length){
            //取后置位数
            appSecret = appSecret.substring(appSecret.length() - length);
        }
        StringBuffer keyValueSB=new StringBuffer();
        String keyValueB = keyValueSB.append(keyValueA).append(BaseConstant.COMBINE)
                .append(HttpConstant.APPSECRET).append(BaseConstant.EQUALS)
                .append(appSecret).toString();

        return keyValueB;
    }


    public static String getSignValue(HttpHeaders headers, TreeMap paramMaps, String appSecret) {

        String keyValueA = getkeyValuesA(headers, paramMaps);

        String keyValueB = getkeyValuesB(keyValueA,appSecret,10);

        String signValue = EncryptUtil.string2SHA256StrJava(keyValueB);
        return signValue;
    }

    /**
     * 验证签名
     *
     * @param paramsMaps
     * @param appSecret  密钥
     * @param sign       签名
     */
    public static boolean validateSignature(TreeMap paramsMaps, String appSecret, String sign) {

        String keyValuesA = getkeyValuesA(paramsMaps);
        String signValue = getSignValue(keyValuesA, appSecret);

        if (sign.equals(signValue)) {
            return true;
        }
        return false;
    }
}
