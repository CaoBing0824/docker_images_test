package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TMenuDetailInfo;
import com.xy.boot.open.mapper.TOpenApiMenuDetailInfoMapper;
import com.xy.boot.open.service.IMenuDetailInfoService;
import org.springframework.stereotype.Service;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Service
public class MenuDetailInfoServiceImpl extends ServiceImpl<TOpenApiMenuDetailInfoMapper, TMenuDetailInfo> implements IMenuDetailInfoService {
}