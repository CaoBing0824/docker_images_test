package com.xy.boot.open.util;

import org.springframework.core.io.UrlResource;

/**
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-04.
 **/
public class ResourceUrlutil {
    /**
     * 判断 网络资源是否存在
     *
     * @param urlName
     * @return
     */
    public static boolean exists(String urlName) {
        try {
            return new UrlResource(urlName).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(exists("https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg"));
        System.out.println("cost:" + (System.currentTimeMillis() - l));
    }
}
