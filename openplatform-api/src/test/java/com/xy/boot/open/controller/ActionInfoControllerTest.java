package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.ActionInfoParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionInfoControllerTest extends BaseTest {

	@Test
	@Transactional
	public void actionInfoUpload01() throws Exception {
		ActionInfoParam param = new ActionInfoParam();
		param.setXyPubCode("PUB397797517535752192");
		param.setSceneCode("01006");
		//param.setXyBtnCode("BUBBLE_BTN399891136774148096");
		param.setBtnName("btnName");
		param.setActionType("OPEN_FAST");
		param.setActionTypeVal("{'ActionTypeVal':'222'}");
		param.setPriority("123");
		param.setStartTime("2020-03-23T06:59:55Z");
		param.setEndTime("2020-04-23T06:59:55Z");
		param.setType("ADD");
		param.setBrandCode("10000001;12000003;13000004;;;");
		ReturnDTO returnDTO = this.post("/actionInfoUpload", JSON.toJSONString(param));
	}

	@Test
	@Transactional
	public void actionInfoUpload() throws Exception {
		ActionInfoParam param = new ActionInfoParam();
		param.setXyPubCode("PUB397797517535752192");
		param.setSceneCode("01006");
//		param.setXyBtnCode("TESTbtnTEST");
		param.setBtnName("btnName");
		param.setActionType("OPEN_URL");
		param.setActionTypeVal("....ActionTypeVal.....");
		param.setPriority("123");
		param.setStartTime("2020-03-23T06:59:55Z");
		param.setEndTime("2020-04-23T06:59:55Z");
		param.setType("ADD");

		ReturnDTO returnDTO = this.post("/actionInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setEndTime("2019-04-23T06:59:55Z");
		returnDTO = this.post("/actionInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setEndTime(null);
		param.setXyPubCode("PUB397797517535752192");
		param.setActionTypeVal("{\"aaa\":\"bbb\"}");
		returnDTO = this.post("/actionInfoUpload", JSON.toJSONString(param));
		String code = JSON.parseObject(String.valueOf(returnDTO.getMessage())).getString("xyCode");
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

		param.setXyPubCode("PUB397797517535752192");
		param.setType("MODIFY");
		param.setXyBtnCode(code);
		param.setPriority("123");
		returnDTO = this.post("/actionInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));
	}
}