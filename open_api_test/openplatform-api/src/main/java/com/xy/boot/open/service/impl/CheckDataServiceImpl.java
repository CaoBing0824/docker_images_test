package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TOpenCheckData;
import com.xy.boot.open.mapper.TOpenCheckDataMapper;
import com.xy.boot.open.service.ICheckDataService;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2019-02-21 10:08:09
 */
@Service
public class CheckDataServiceImpl extends ServiceImpl<TOpenCheckDataMapper, TOpenCheckData> implements ICheckDataService {
}