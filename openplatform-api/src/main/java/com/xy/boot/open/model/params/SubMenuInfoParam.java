package com.xy.boot.open.model.params;

import com.xy.boot.open.entity.enums.SubMenuActionTypeEnum;
import com.xy.boot.open.model.annotation.EnumValidAnnotation;
import com.xy.boot.open.model.annotation.JsonAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 公众号信息接口参数
 *
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2019-01-02 下午 03:36
 */
@Data
public class SubMenuInfoParam {
    /**
     * 菜单名称
     */
    @Length(max = 20, message = "子菜单名称不能超过20个字符")
    @NotBlank(message = "子菜单名称不能为空")
    private String subItemName;
    /**
     * 菜单类型
     */
    @EnumValidAnnotation(message = "菜单类型错误", target = SubMenuActionTypeEnum.class)
    @NotBlank(message = "子菜单类型不能为空")
    private String subItemType;
    /**
     * 菜单配置内容
     */
    @JsonAnnotation(message = "子菜单配置内容须为有效json字符")
    @Length(max = 2048, message = "子菜单配置内容不能超过2048个字符")
    private String subItemTypeVal;

}
