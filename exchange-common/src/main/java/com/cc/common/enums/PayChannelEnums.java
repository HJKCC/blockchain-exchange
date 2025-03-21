package com.cc.common.enums;

/**
 * @author chencheng0816@gmail.com
 */
public enum PayChannelEnums {

    /**
     * 支付宝
     */
    ALIPAY("aliPay", "支付宝"),

    /**
     * 微信
     */
    WX("wx", "微信");

    private String code;

    private String desc;

    PayChannelEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public static PayChannelEnums getByCode(String code) {
        for (PayChannelEnums payChannel : PayChannelEnums.values()) {
            if(payChannel.getCode().equals(code)) {
                return payChannel;
            }
        }
        return null;
    }
}
