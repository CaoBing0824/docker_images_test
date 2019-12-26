package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TAgentExtInfo;
import com.xy.boot.open.mapper.TOpenApiAgentExtInfoMapper;
import com.xy.boot.open.service.IAgentExtInfoService;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Service
public class AgentExtInfoServiceImpl extends ServiceImpl<TOpenApiAgentExtInfoMapper, TAgentExtInfo> implements IAgentExtInfoService {
}