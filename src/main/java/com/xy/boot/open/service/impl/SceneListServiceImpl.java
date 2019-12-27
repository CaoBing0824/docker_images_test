package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TOpenSceneList;
import com.xy.boot.open.mapper.TOpenSceneListMapper;
import com.xy.boot.open.service.ISceneListService;
import org.springframework.stereotype.Service;

/**
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2019-01-08 16:36:48
 */
@Service
public class SceneListServiceImpl extends ServiceImpl<TOpenSceneListMapper, TOpenSceneList> implements ISceneListService {
}