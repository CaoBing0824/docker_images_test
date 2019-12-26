package com.xy.boot.open.controller;

import com.alibaba.fastjson.JSON;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.open.model.params.RelationInfoParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuRelationInfoControllerTest extends BaseTest {


	@Test
	@Transactional
	public void relationInfoUpload() throws Exception {
		RelationInfoParam param = new RelationInfoParam();
		param.setType("ADD");
		param.setAgPhoneId("999");
		param.setXyPubCode("PUB397801832459542528");

		/** 非必填，小源号码编码 */
		param.setXyNumCode("ENT396309952735535104");

		/** 必填，小源菜单编码 */
		param.setXyMenuCode("MENU399953562626465792");

		/** 非必填，用户标识Id，最大长度=50 */
		param.setAgPhoneId("54444");

		/** 必填，区域1.5 区域省份编码对应表，多个英文分号隔开‘;’最大长度=500 */
		param.setAreas("12;31;44;15");

		ReturnDTO returnDTO = this.post("/relationInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setXyNumCode("CHANNEL399649268316524544");
		returnDTO = this.post("/relationInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

		param.setType("MODIFY");
		returnDTO = this.post("/relationInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));


		param.setType("MODIFY");
		param.setXyRelationCode("MENU_RELATION401438182178037760");
		returnDTO = this.post("/relationInfoUpload", JSON.toJSONString(param));
		Assert.assertTrue((returnDTO.getCode() == HttpCodeEnum.UNPROCESABLE_ENTITY.getCode()));

	}
}