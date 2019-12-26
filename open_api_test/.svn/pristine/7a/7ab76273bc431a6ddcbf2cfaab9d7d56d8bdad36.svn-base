package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TMuiltData;
import com.xy.boot.open.entity.TNumInfo;
import com.xy.boot.open.mapper.TOpenApiMuiltDataMapper;
import com.xy.boot.open.service.IMuiltDataService;
import com.xy.boot.open.util.GenIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 多值信息
 *
 * @author heyuancheng
 * @email heyuancheng@mfexcel.com
 * @date 2018-12-27 14:34:21
 */
@Service
public class MuiltDataServiceImpl extends ServiceImpl<TOpenApiMuiltDataMapper, TMuiltData> implements IMuiltDataService {

    @Autowired
    private GenIDUtil genIDUtil;

    @Override
    public long saveMuiltData(final List<String> datas) {
        //排重
        HashSet h = new HashSet(datas);
        datas.clear();
        datas.addAll(h);
        if (!CollectionUtils.isEmpty(datas)) {
            List<TMuiltData> lists = new ArrayList<>();
            long relationId = genIDUtil.nextId();
            for (String data : datas) {
                if (!StringUtils.isEmpty(data)) {
                    TMuiltData muiltData = new TMuiltData();
                    muiltData.setRelationId(relationId);
                    muiltData.setValue(data);
                    lists.add(muiltData);
                }
            }
            if (!CollectionUtils.isEmpty(lists)) {
                this.insertBatch(lists);
                return relationId;
            }
        }
        return 0;
    }

    @Override
    public String getMuiltDataStr(final Long relationId) {
        EntityWrapper<TMuiltData> qryWrapper = new EntityWrapper<>();
        qryWrapper.eq(TMuiltData.TB_RELATION_ID, relationId);
        List<TMuiltData> tMuiltData = this.selectList(qryWrapper);
        StringBuffer buffer = new StringBuffer();
        tMuiltData.forEach(data -> {
            buffer.append(data.getValue()).append(";");
        });
       return buffer.toString();
    }

}