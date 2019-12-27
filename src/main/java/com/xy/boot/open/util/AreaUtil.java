package com.xy.boot.open.util;

import com.xy.boot.open.model.constvar.SysConstVar;

import java.util.ArrayList;
import java.util.List;

public class AreaUtil {
    private static List<String> areaLs;

    static {
        areaLs = new ArrayList<>(32);
        areaLs.add("11");
        areaLs.add("12");
        areaLs.add("13");
        areaLs.add("14");
        areaLs.add("15");
        areaLs.add("21");
        areaLs.add("22");
        areaLs.add("23");
        areaLs.add("31");
        areaLs.add("32");
        areaLs.add("33");
        areaLs.add("34");
        areaLs.add("35");
        areaLs.add("36");
        areaLs.add("37");
        areaLs.add("41");
        areaLs.add("42");
        areaLs.add("43");
        areaLs.add("44");
        areaLs.add("45");
        areaLs.add("46");
        areaLs.add("50");
        areaLs.add("51");
        areaLs.add("52");
        areaLs.add("53");
        areaLs.add("54");
        areaLs.add("61");
        areaLs.add("62");
        areaLs.add("63");
        areaLs.add("64");
        areaLs.add("65");
        areaLs.add(SysConstVar.CN_AREA_CODE);
    }

    public static boolean contains(String areaCode) {
        return areaLs.contains(areaCode);
    }

    public static boolean contains(List<String> areaCodes) {
        for (String code : areaCodes) {
            if (!areaLs.contains(code)) {
                return false;
            }
        }
        return true;
    }
}
