package com.xy.boot.open.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum ActionTypeEnum implements IEnum {
	SEND_SMS("SEND_SMS", "发送短信"),
	OPEN_URL("OPEN_URL", "打开URL"),
	CALL("CALL", "拨打电话"),
	OPEN_APP("OPEN_APP", "打开APP"),
	MENU("MENU", "打开子菜单"),
	OPEN_FAST("OPEN_FAST", "打开快应用");

	private final String type;
	private final String desc;

	ActionTypeEnum(final String type, final String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}


	@Override
	public Serializable getValue() {
		return this.type;
	}

	/**
	 * 根据type获取枚举类
	 *
	 * @param type
	 * @return
	 */
	public static ActionTypeEnum getEnum(String type) {
		for (ActionTypeEnum item : ActionTypeEnum.values()) {
			if (type.equals(item.getType())) {
				return item;
			}
		}
		return null;
	}
}

