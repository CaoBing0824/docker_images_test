package com.xy.boot.open.util;

import com.xy.boot.open.entity.enums.DataTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 小源Code生成类
 *
 * @author heyuancheng@mfexcel.com
 *         Date:2018-12-28.
 **/
@Slf4j
@Component
public class XyCodeUtil {

    @Autowired
    private GenIDUtil genIDUtil;

    @Autowired
    private XyAgentCodeHolder xyAgentCodeHolder;

    /**
     * 获取xy_code
     *
     * @param dataType 数据类型 DataTypeEnum
     * @return
     */
    public String getXyCode(DataTypeEnum dataType) {
        if (StringUtils.isEmpty(dataType)) {
            log.error("dataType is null");
            return "";
        }
        if (genIDUtil == null) {
            log.error("genIDUtil is null,please use Autowired XyCodeUtil");
            return "";
        }
        return dataType.getType() + genIDUtil.nextId();
    }

    /**
     * 获取代理商ID
     *
     * @return
     */
    public String getAgentCode() {
        return xyAgentCodeHolder.getAgentCode();
    }

}
