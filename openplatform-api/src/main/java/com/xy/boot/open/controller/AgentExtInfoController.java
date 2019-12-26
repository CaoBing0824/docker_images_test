package com.xy.boot.open.controller;

import com.xy.boot.common.base.controller.BaseController;
import com.xy.boot.open.entity.TAgentExtInfo;
import com.xy.boot.open.service.IAgentExtInfoService;
import org.springframework.web.bind.annotation.*;
/**
* @Author: heyuancheng@mfexcel.com
* @Date: 2018-12-27 下午 02:15
*/
@RestController
@RequestMapping("/tOpenApiAgentExtInfo")
public class AgentExtInfoController extends BaseController<TAgentExtInfo, IAgentExtInfoService> {
}