package com.xy.boot.open.util;


import com.xy.boot.open.model.constvar.SysConstVar;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author heyuancheng@mfexcel.com
 *         Date:2019-01-02.
 **/
public class StringUtils {

    private static Pattern PATTERN_NUMERIC = Pattern.compile("[0-9]\\d*\\.?\\d*");
    private static Pattern PATTERN_INTEGER = Pattern.compile("^[-\\+]?[\\d]*$");
    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = PATTERN_NUMERIC.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 利用正则表达式判断字符串是否是正整数
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Matcher isNum = PATTERN_INTEGER.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 用于英文;号切割字符串，返回去重 去空 集合
     * @param str
     * @return
     */
    public static List<String> getList(String str){
        if(org.springframework.util.StringUtils.isEmpty(str)){
            return null;
        }
        List<String> ls =  new ArrayList<>(Arrays.asList(str.split(SysConstVar.ENG_REDNIK)));
        HashSet hs = new HashSet(ls);
        ls.clear();
        ls.addAll(hs);
        ls.removeAll(Collections.singleton(""));
        return ls;
    }
}
