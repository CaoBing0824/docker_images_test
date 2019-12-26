package com.xy.boot.open.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@Mapper
public interface TOpenApiPubExtInfoMapper {

    /**
     * 根据公众号code获取菜单code
     * @param pubCode
     * @return
     */
    String getPubIdByPubCode(@Param("pubCode") String pubCode);

    /**
     * 查询公众号失效时间
     * @param pubCode
     * @return
     */
    Date getExpiryDateByPubCode(@Param("pubCode") String pubCode);

    /**
     * 更新验收状态
     * @param pubCode
     * @param desc
     * @param status
     * @return
     */
    void updateAcceptanceStatusByPubCode(@Param("pubCode") String pubCode,@Param("desc") String desc,@Param("status") String status);

    /**
     * 获取验收状态
     * @param pubCode
     * @return
     */
    String getAcceptanceStatusByPubCode(@Param("pubCode") String pubCode);

}
