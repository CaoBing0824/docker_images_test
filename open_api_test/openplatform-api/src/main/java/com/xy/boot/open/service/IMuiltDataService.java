package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TMuiltData;

import java.util.List;

/**
 * 多值
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IMuiltDataService extends IService<TMuiltData> {

    /**
     * 保存多值信息
     *
     * @param datas
     * @return 关联Id
     */
    long saveMuiltData(List<String> datas);

    /**
     * 根据关联id获取列表
     *
     * @param relationId
     * @return
     */
    String getMuiltDataStr(Long relationId);

}