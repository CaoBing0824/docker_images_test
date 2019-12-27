
package com.xy.boot.open.util.result;

import java.util.HashMap;
import java.util.Map;

public class ResultDesciptionHash {
    private static final Map<Integer, String> hash = new HashMap();

    public ResultDesciptionHash() {
    }

    public static String getDescription(int status) {
        return hash.get(Integer.valueOf(status)) == null ? "很抱歉，系统处理出现异常" : (String) hash.get(Integer.valueOf(status));
    }

    static {
        hash.put(Integer.valueOf(1), "成功");
        hash.put(Integer.valueOf(0), "失败");
    }
}
