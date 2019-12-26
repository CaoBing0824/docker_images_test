package com.xy.boot.open.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.boot.open.entity.TBrandInfo;
import com.xy.boot.open.mapper.TOpenApiBrandInfoMapper;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.service.IBrandInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BrandInfoServiceImpl extends ServiceImpl<TOpenApiBrandInfoMapper, TBrandInfo> implements IBrandInfoService {

    @Autowired
    private TOpenApiBrandInfoMapper tOpenApiBrandInfoMapper;


    @Override
    public List<String> getBrandCodeByCode(List<String> brandCodes) {
        return tOpenApiBrandInfoMapper.getBrandCodeByCode(brandCodes);
    }

    //非空情况下判断 个数不能超过配置的个数 单个长度不超过11
    @Override
    public String isBrandCodeValid(String brandCodes,int count ,int len) {
        //允许为空
        String check = null;
        if (StringUtils.isEmpty(brandCodes)) {
            return null;
        }
        //个数不能超过配置的个数
        String[] brands = brandCodes.split(SysConstVar.ENG_REDNIK);
        if (brands.length > count) {
            check = "品牌编码个数不能超过"+count+"个";
        }
        //单个长度不能超过限制
        boolean isPresent = Stream.of(brands).filter(b -> (b.length() > len)).findAny().isPresent();
        if(isPresent){
            check = "品牌编码单个长度不超过"+len;
        }
        return check;

    }

    //编码是否存在
    @Override
    public List<String> checkBrandExists(String brandCode){
        //上报时检测品牌上报是否在品牌列表中
        if (!StringUtils.isEmpty(brandCode) && brandCode.split(SysConstVar.ENG_REDNIK).length > 0) {
            List<String> brandCodeList = Arrays.asList(brandCode.split(SysConstVar.ENG_REDNIK));
            List<String> brandCodes =   this.getBrandCodeByCode(brandCodeList);
            List<String> notExistsCodes = new ArrayList<>();
            if(brandCodes.containsAll(brandCodeList)){
                return null;
            }  else {
                //查找不存在的品牌代码
                for(String code : brandCodeList){
                    if(!brandCodes.contains(code) && !StringUtils.isEmpty(code)){
                        notExistsCodes.add(code);
                    }
                }
                return notExistsCodes;
            }
        }else{
            return null;
        }
    }
}
