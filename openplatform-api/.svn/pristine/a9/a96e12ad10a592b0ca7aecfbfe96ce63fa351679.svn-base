package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.WhiteInfoParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiteInfoControllerTest extends BaseTest{

	@Test
	@Transactional
	public void whiteInfoUpload() throws Exception{
		WhiteInfoParam param = new WhiteInfoParam();
		param.setType("ADD");
		param.setImeis("41235;sfds234;dfdhd6346");
		param.setPhoneType("meizu3");
		param.setPhoneBrand("meizu");
		param.setXyPubCodes("123;236;555");
		//get执行 返回成ReturnDTO对象
		ReturnDTO returnDTO =this.post("/whiteInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setXyPubCodes("PUB397801832459542528");
		param.setImeis("457457457;29385275983kj");
		returnDTO =this.post("/whiteInfoUpload", JSON.toJSONString(param));
		String xyCode = JSON.parseObject(String.valueOf(returnDTO.getMessage())).getString("xyCode");
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

		param.setType("MODIFY");
		param.setPhoneBrand(null);
		returnDTO =this.post("/whiteInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setXyWhiteCode(xyCode);
		param.setPhoneBrand("afasf");
		returnDTO =this.post("/whiteInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

	}
}