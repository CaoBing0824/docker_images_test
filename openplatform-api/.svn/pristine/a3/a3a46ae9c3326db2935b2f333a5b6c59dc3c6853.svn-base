package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.model.annotation.AgentSceneAnnotation;
import com.xy.boot.open.model.annotation.XyCodeValidAnnotation;
import com.xy.boot.open.model.params.SmsParseInfoParam;
import org.hibernate.validator.constraints.Length;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsInfoControllerTest extends BaseTest {

	@Test
	@Transactional
	public void smsParseInfoUpload() throws Exception{
		SmsParseInfoParam param = new SmsParseInfoParam();
		param.setXyPubCode("PUB399577333012418560");
		param.setType("ADD");
// 小源卡片编码，(首次为空、更新不能为空)
//		param.setXySmsCode("gagagag");
		param.setSceneCode("01010");
		param.setSmsContent("合伙人和任何人说都是大公司大厦嘎嘎嘎");
		param.setSmsTemplate("看啊，；阿萨刚好热乎劲儿和");
//		必填，期望字段提取，最大长度=1000  消费金额：300元;卡号：4611;消费时间：11日12:16;交易类型：ATM支持（取款）;手续费：4元 英文分号隔开
		param.setParseContent("和华发峰:asfasf;;;;:爱福家南方;爱福家南方");

		ReturnDTO returnDTO = this.post("/smsParseInfoUpload", JSON.toJSONString(param));
		String xyCode = JSON.parseObject(String.valueOf(returnDTO.getMessage())).getString("xyCode");
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.OK.getCode()));

		param.setXySmsCode(xyCode);
		returnDTO = this.post("/smsParseInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setXySmsCode(xyCode);
		param.setType("MODIFY");
		param.setSmsTemplate("阿萨法举案说法");
		returnDTO = this.post("/smsParseInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));
	}
}