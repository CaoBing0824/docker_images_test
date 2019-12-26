package com.xy.boot.open.util;

/**
 *  含中文的字符串长度检查
 *
 * @author maisenlin@mfecel.com
 * @since 2019-05-30
 */
public class StringLengthVaildUtil {

    /**
     * 长度小于等于criticalLength
     * @param criticalLength 临界长度
     * @param targetStr 待检字串
     * @return
     */
    public static boolean isLte(int criticalLength, String targetStr){
        return lengthOfStr(targetStr) <= criticalLength;
    }

    /**
     * 长度大于criticalLength
     * @param criticalLength 临界长度
     * @param targetStr 待检字串
     * @return
     */
    public static boolean isGt(int criticalLength, String targetStr){
        return lengthOfStr(targetStr) > criticalLength;
    }

    /**
     * 字串长度
     * @param str
     * @return
     */
    private static int lengthOfStr(String str){
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < str.length(); i++) {
            /* 获取一个字符 */
            String temp = str.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }

        return valueLength;
    }
}
