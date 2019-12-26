package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 审核状态码对应信息
 *
 * @Author: tangchao@mfexcel.com
 * @Date: 2018-12-29 下午 15:10
 */
public enum CheckStatusEnum implements IEnum {

        DATA_STATUS_INVALID_RECORD(-1, "数据无效记录"),
        DATA_STATUS_DRAFT(0, "草稿"),
        DATA_STATUS_SUBMIT(1, "待审核"),
        DATA_STATUS_REJECT(2, "审核不通过"),
        DATA_STATUS_OK(3, "审核通过"),
        DATA_STATUS_CANCEL(4, "数据作废"),
        DATA_STATUS_RELEASE(5, "已上线"),
        DATA_STATUS_CONFIRM_OFFLINE(6, "待下线"),
        DATA_STATUS_OFFLINE(7, "已下线"),
        DATA_STATUS_TIMEOUT(8, "已过期"),
        DATA_STATUS_CONFIRM_RELEASE(9, "待上线"),
        DATA_STATUS_WHITE_MENU_RELEASE(11, "白名单上线"),
        DATA_STATUS_WHITE_MENU_OFFLINE(12, "白名单下线"),
        DATA_STATUS_WHITE_MENU_EXPIRY(20, "合作到期");

        private final Integer statusCode;
        private final String statusDesc;

        CheckStatusEnum(final Integer statusCode, final String statusDesc) {
            this.statusCode = statusCode;
            this.statusDesc = statusDesc;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public static CheckStatusEnum getCheckStatusEnumByStatusCode(Integer statusCode){
            for (CheckStatusEnum checkStatusEnum: CheckStatusEnum.values()) {
                if(statusCode.equals(checkStatusEnum.statusCode)){
                    return checkStatusEnum;
                }
            }
            return null;
        }

    @Override
    public Serializable getValue() {
        return this.statusCode;
    }
}
