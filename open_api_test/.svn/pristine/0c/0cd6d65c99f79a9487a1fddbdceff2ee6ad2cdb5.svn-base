package com.xy.boot.open.model.annotation.validator;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.open.entity.TOpenAgentScene;
import com.xy.boot.open.entity.TOpenSceneList;
import com.xy.boot.open.model.annotation.AgentSceneAnnotation;
import com.xy.boot.open.service.IAgentSceneService;
import com.xy.boot.open.service.ISceneListService;
import com.xy.boot.open.util.XyAgentCodeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgentSceneValidtor implements ConstraintValidator<AgentSceneAnnotation, String> {

	@Autowired
	private IAgentSceneService service;


	@Autowired
	private ISceneListService sceneListService;

	@Autowired
	private XyAgentCodeHolder agentCodeHolder;

	@Override
	public boolean isValid(String scenecode, ConstraintValidatorContext constraintValidatorContext) {
		if (!StringUtils.isEmpty(scenecode)) {
			EntityWrapper<TOpenAgentScene> ew = new EntityWrapper<>();
			//ew.eq(TOpenAgentScene.TB_SCENE_CODE, scenecode);
			ew.eq(TOpenAgentScene.TB_XY_AGENT_CODE, agentCodeHolder.getAgentCode());
			Integer count = service.selectCount(ew);
			//非限制代理商
			if (count == null || count == 0) {
				EntityWrapper<TOpenSceneList> ew2 = new EntityWrapper<>();
				ew2.eq(TOpenSceneList.TB_SCENE_CODE, scenecode);
				Integer count2 = sceneListService.selectCount(ew2);
				if (count2 > 0) {
					return true;
				}
				return false;
			}else{
				//限制代理商
				ew.eq(TOpenAgentScene.TB_SCENE_CODE, scenecode);
				Integer count3 = service.selectCount(ew);
				if (count3 > 0) {
					return true;
				}
				return false;
			}
		}
		return true;
	}
}
