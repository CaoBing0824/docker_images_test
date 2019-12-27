package com.xy.boot.open.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.base.entity.ReturnDTO;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.open.entity.TNumInfo;
import com.xy.boot.open.entity.TPubInfo;
import com.xy.boot.open.entity.enums.ChannelNumStatusEnum;
import com.xy.boot.open.entity.enums.DataTypeEnum;
import com.xy.boot.open.entity.enums.ProductTypeEnum;
import com.xy.boot.open.model.constvar.SysConstVar;
import com.xy.boot.open.model.enums.OperateTypeEnum;
import com.xy.boot.open.model.params.ChannelInfoParam;
import com.xy.boot.open.service.INumInfoService;
import com.xy.boot.open.service.IPubInfoService;
import com.xy.boot.open.service.PublicReceiveNumReleaseService;
import com.xy.boot.open.util.ParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 号码信息
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-27 下午 02:15
 */
@Slf4j
@RestController
public class NumInfoController {

    @Autowired
    private INumInfoService iNumInfoService;

    @Autowired
    private PublicReceiveNumReleaseService publicReceiveNumReleaseService;

    @Autowired
    private IPubInfoService iPubInfoService;

    private final String AREA_CN = "CN;";

    @PostMapping({"/channelsUpload"})
    public ReturnDTO channelsUpload(@RequestBody @Valid ChannelInfoParam param, BindingResult mesResult) {
        //type=ADD，xyEntCode为空时，提示改为，新增时，xyEntCode要为空
        String errorMsg = ParamUtils.validCodeAndType(param.getXyChannelCode(), param.getType(), DataTypeEnum.CHANNEL.getDesc());
        if (!StringUtils.isEmpty(errorMsg)) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), errorMsg);
        }
        //参数验证
        if (mesResult.hasErrors()) {
            return ReturnDTOUtil.opCheckError(mesResult);
        }
        //验证修改时通道号是否在公众号下
        if (OperateTypeEnum.MODIFY.getType().equalsIgnoreCase(param.getType()) ) {
            EntityWrapper<TNumInfo> ew = new EntityWrapper<>();
            ew.eq(TNumInfo.TB_XY_PUB_CODE, param.getXyPubCode());
            ew.eq(TNumInfo.TB_XY_CODE, param.getXyChannelCode());
            Integer count = iNumInfoService.selectCount(ew);
            if (count == null || count == 0) {
                return ReturnDTOUtil.opCheckError("该通道号不存在于该公众号下");
            }
        }

        // 公众号类型为黄页时，且上传的通道号不为空时，检测通道号是否已收录户部(只查询区域为CN;下已启用的通道号)
        //去除 公众号类型为黄页时对通道号的限制 RR-3674
        TPubInfo tpubInfo = iPubInfoService.getOne(param.getXyPubCode());

        if (!checkReceiveNumPattern(param.getReceiveNum(), tpubInfo.getProductType().getType())) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), ProductTypeEnum.YELLOW.getType().equals(tpubInfo.getProductType().getType()) ? "黄页通道号格式错误，仅支持20位以内数字" : "通道号表示错误,数字或数字+星号或者号段1069***~1069****");
        }

/*        if( ProductTypeEnum.YELLOW.equals(tpubInfo.getProductType())
                && StringUtils.isNotEmpty(param.getReceiveNum()) && OperateTypeEnum.ADD.getType().equals(param.getType())){
            String publicNumResult = publicReceiveNumReleaseService.getOneByCondition(param.getReceiveNum() , AREA_CN, ChannelNumStatusEnum.ENABLE.getCode());
            if( StringUtils.isNotEmpty(publicNumResult) ){
                log.info("[已拒收] 公众号编码：{} 通道号编码：{} 通道号：{}, 错误：通道号已被占用", param.getXyPubCode(), param.getXyChannelCode(), param.getReceiveNum());
                return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY.getCode(), "通道号已被占用");
            }
        }*/

        try {
            return iNumInfoService.upload(param);
        } catch (Exception e) {
            log.error("异常{}", e);
        }
        return ReturnDTOUtil.custom(HttpCodeEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 校验通道号格式
     *
     * @param receiveNum
     * @param type
     * @return
     */
    private static boolean checkReceiveNumPattern(String receiveNum, String type) {
        if (!org.springframework.util.StringUtils.isEmpty(receiveNum)) {
            //取消 针对黄页通道号数据单独处理
/*            if (type != null && type.equals(ProductTypeEnum.YELLOW.getType())) {
                if (yellowReceiveNumIsNumeric(receiveNum)) {
                    return true;
                } else {
                    return false;
                }
            } else */
            if (com.xy.boot.open.util.StringUtils.isNumeric(receiveNum.replaceAll(SysConstVar.ASTERISK_STR, ""))) {
                return true;
            } else {
                if (receiveNum.contains(SysConstVar.SPLIT_B)) {
                    String[] nums = receiveNum.split(SysConstVar.SPLIT_B);
                    if (nums.length == 2) {
                        boolean result = true;
                        for (int i = 0; i < nums.length; i++) {
                            if (org.springframework.util.StringUtils.isEmpty(nums[i]) || !com.xy.boot.open.util.StringUtils.isNumeric(nums[i].replaceAll("\\*", ""))) {
                                result = false;
                                break;
                            }
                        }
                        if (result) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 校验数据格式
     *
     * @param receiveNum
     * @return
     */
    private static boolean yellowReceiveNumIsNumeric(String receiveNum) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(receiveNum);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}