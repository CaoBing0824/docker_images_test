package com.xy.boot.open.util;

import com.xy.boot.open.model.constvar.SysConstVar;

/**
 * 图片工具类
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-04.
 **/
public class ImageUtils {
    /**
     * 判断图片文件后缀是否为 jpg png
     *
     * @param fileName
     * @return
     */
    public static boolean checkSuffix(String fileName) {
        if (org.springframework.util.StringUtils.isEmpty(fileName)) {
            return false;
        }
        if (fileName.toLowerCase().endsWith(SysConstVar.JPG_FILE_SUFFIX) || fileName.toLowerCase().endsWith(SysConstVar.PNG_FILE_SUFFIX)) {
            return true;
        }
        return false;
    }
}
