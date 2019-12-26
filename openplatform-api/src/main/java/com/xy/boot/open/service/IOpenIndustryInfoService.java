package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.open.entity.TOpenIndustryInfo;
import com.xy.boot.open.entity.enums.IndustryInfoStatusEnum;

/**
 * 行业信息
 * @author maisenlin
 * @email maisenlin@mfexcel.com
 * @date 2019-06-25
 */
public interface IOpenIndustryInfoService extends IService<TOpenIndustryInfo> {

    /**
     * 检查是否允许黄页上报
     * @param industryCode 行业编码
     * @param status IndustryInfoStatusEnum
     * @return
     *        true：表示行业编码状态与status匹配
     *        false: 表示行业编码状态与status不匹配
     */
    boolean checkIndustryCode(String industryCode, IndustryInfoStatusEnum status);

    /**
     * 是否存在industryCode
     * @param industryCode 行业编码
     * @return
     *        true : 存在
     *        false : 不存在
     */
    boolean existIndustryCode(String industryCode);
}
