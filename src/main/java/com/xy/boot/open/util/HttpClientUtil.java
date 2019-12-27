package com.xy.boot.open.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.util.*;

/**
 * Created by fuwenshen
 * Date:2018/10/18
 * Time:10:45
 * HTTP 网络请求工具
 */
public class HttpClientUtil {

    static CloseableHttpClient client = null;
    static {
        client = HttpClients.createDefault();
    }

    /**
     *
     * 方法: get <br>
     * 描述: get请求 <br>
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String get(String url,Map<String, Object> params){
        try {
            HttpGet httpGet = new HttpGet();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(url);
            if(!CollectionUtils.isEmpty(params)){
                stringBuffer.append("?");
                Set<Map.Entry<String, Object>> entrySet = params.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Object> entry = iterator.next();
                    stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            httpGet.setURI(new URI(stringBuffer.toString()));
            CloseableHttpResponse execute = client.execute(httpGet);
            return EntityUtils.toString(execute.getEntity(), "utf-8");
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *
     * 方法: post <br>
     * 描述: post请求 <br>
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String post(String url,Map<String, Object> params) {
        try {
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(new URI(url));
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            if(!CollectionUtils.isEmpty(params)){
                Set<Map.Entry<String, Object>> entrySet = params.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Object> entry = iterator.next();
                    NameValuePair e = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    parameters.add(e);
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse execute = client.execute(httpPost);
            return EntityUtils.toString(execute.getEntity(), "utf-8");
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
