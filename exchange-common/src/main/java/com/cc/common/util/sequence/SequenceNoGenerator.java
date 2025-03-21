package com.cc.common.util.sequence;

import com.cc.common.enums.PayChannelEnums;

/**
 * @author chencheng0816@gmail.com
 */
public interface SequenceNoGenerator {

    /**正式环境订单前缀*/
    String PROD_PREFIX = "6";

    /**测试环境订单前缀*/
    String TEST_PREFIX = "3";

    /**开发环境订单前缀*/
    String DEV_PREFIX = "1";

    /**微信支付交易流水号前缀*/
    String WX_PAY_PREFIX = "6";

    /**支付宝支付交易流水号前缀*/
    String ALIPAY_PREFIX = "8";

    /**退款交易流水号前缀*/
    String REFUND_NO_PREFIX = "4";

    /**
     * 生成唯一序列号
     * @param businessPrefix
     * @return
     */
    String generate(String businessPrefix, String sequenceNoType);

    /**
     * 生成商品订单号
     * @param orderType 商品类型
     * @return
     */
    String getOrderNo(String orderType);

    /**
     * 生成第三方交易号，支付单号
     * @param payChannel
     * @return
     */
    String getOutTradeNo(PayChannelEnums payChannel);

}
