package com.xy.boot.open.util;

import org.joda.time.DateTime;

/**
 * 小源Version生成类
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2018-12-28.
 **/
public class XyVersionUtil {

    /**
     * @return xy_version
     */
    public static String getXyVersion() {
        return DateTime.now().toString("yyyyMMddHHmmssSSS");
    }
}
