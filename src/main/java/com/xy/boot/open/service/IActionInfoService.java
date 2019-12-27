package com.xy.boot.open.service;

import com.baomidou.mybatisplus.service.IService;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.open.entity.TActionInfo;
import com.xy.boot.open.model.params.ActionInfoParam;

/**
 * 
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
public interface IActionInfoService extends IService<TActionInfo> {

	ReturnDTO actionInfoUpload(ActionInfoParam param);
}