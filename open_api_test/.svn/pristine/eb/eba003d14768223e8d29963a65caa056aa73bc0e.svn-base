package com.xy.boot.open.entity.enums;

import java.util.Arrays;

/*** Copyright (C), 2006-2020, xy
 * FileName: Test.java
 *
 ** @author wuwenguan
 * @date 2019/2/16
 * @version 1.00*/
public enum ApiAreaEnum {
    CN(86, "全国", "CN"),
    BJ(11, "北京", "BJ"),
    TJ(12, "天津", "TJ"),
    HB(13, "河北", "HB"),
    SX(14, "山西", "SX"),
    NM(15, "内蒙古", "NM"),
    LN(21, "辽宁", "LN"),
    JL(22, "吉林", "JL"),
    HL(23, "黑龙江", "HL"),
    SH(31, "上海", "SH"),
    JS(32, "江苏", "JS"),
    ZZ(33, "浙江", "ZZ"),
    AW(34, "安徽", "AW"),
    FJ(35, "福建", "FJ"),
    JX(36, "江西", "JX"),
    SD(37, "山东", "SD"),
    HN(41, "河南", "HN"),
    WH(42, "湖北", "WH"),
    CS(43, "湖南", "CS"),
    GD(44, "广东", "GD"),
    GX(45, "广西", "GX"),
    HK(46, "海南", "HK"),
    CQ(50, "重庆", "CQ"),
    SC(51, "四川", "SC"),
    GZ(52, "贵州", "GZ"),
    YN(53, "云南", "YN"),
    XZ(54, "西藏", "XZ"),
    XA(61, "陕西", "XA"),
    GS(62, "甘肃", "GS"),
    QH(63, "青海", "QH"),
    NX(64, "宁夏", "NX"),
    XJ(65, "新疆", "XJ"),
    TW(71, "台湾", "TW"),
    XG(81, "香港", "XG"),
    OM(82, "澳门", "OM");


    private final Integer code;
    private final String desc;
    private final String pubCode;
    private final static String SPLIT = ";";

    ApiAreaEnum(final Integer code, final String desc, final String pubCode) {
        this.code = code;
        this.desc = desc;
        this.pubCode = pubCode;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getPubCode() {
        return pubCode;
    }

    public static ApiAreaEnum getDataTypeEnumByCode(Integer code) {
        for (ApiAreaEnum dataTypeEnum : ApiAreaEnum.values()) {
            if (code.equals(dataTypeEnum.getCode())) {
                return dataTypeEnum;
            }
        }
        return null;
    }

    /**
     * 通过省份名获取省份编码
     * @param area
     * @return
     */

    public static String getCodeByDesc(String area) {
        for (ApiAreaEnum dataTypeEnum : ApiAreaEnum.values()) {
            if (area.equals(dataTypeEnum.getDesc())) {
                return dataTypeEnum.getCode()+"";
            }
        }
        return null;
    }

    /**
     * 区域转换，将以;分割的区域编码转换为中文解释
     *
     * @param codes 需要转换的字符串，如 86;11
     * @return 转换后的结果 如:全国;北京
     */
    public static String getAreaDesc(String codes) {
        String[] codeList = codes.split(SPLIT);
        StringBuilder resultString = new StringBuilder(10);
        Arrays.asList(codeList).forEach(string -> {
            for (ApiAreaEnum dataTypeEnum : ApiAreaEnum.values()) {
                if (string.equals(dataTypeEnum.getCode().toString())) {
                    resultString.append(dataTypeEnum.getDesc()).append(SPLIT);
                }
            }
        });
        //去除末尾分号
        if (resultString.length() > 0) {
            resultString.setLength(resultString.length() - 1);
        }
        return resultString.toString();
    }

    /**
     * 区域转换，将以;分割的区域编码转换为户部编码
     *
     * @param codes 需要转换的字符串，如 86;11
     * @return 转换后的结果 如:全国;北京
     */
    public static String getAreaPubCode(String codes) {
        String[] codeList = codes.split(SPLIT);
        StringBuilder resultString = new StringBuilder(10);
        Arrays.asList(codeList).forEach(string -> {
            for (ApiAreaEnum dataTypeEnum : ApiAreaEnum.values()) {
                if (string.equals(dataTypeEnum.getCode().toString())) {
                    resultString.append(dataTypeEnum.getPubCode()).append(SPLIT);
                }
            }
        });
        //去除末尾分号
        if (resultString.length() > 0) {
            resultString.setLength(resultString.length() - 1);
        }
        return resultString.toString();
    }

}
