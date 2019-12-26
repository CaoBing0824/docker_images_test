package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.CheckInfoQueryParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckinfoQueryControllerTest extends BaseTest{

	@Test
	@Transactional
	public void checkInfoQuery() throws Exception{
		CheckInfoQueryParam param = new CheckInfoQueryParam();
		param.setXyCode("CHANNEL399659559636996096");
		//post执行 返回成ReturnDTO对象
		ReturnDTO returnDTO =this.post("/checkInfoQuery", JSON.toJSONString(param));
		Assert.assertTrue(((List)returnDTO.getMessage()).size() > 0);
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
	}
}