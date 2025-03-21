package com.cc.common.util.sequence;

import com.cc.common.enums.PayChannelEnums;
import com.cc.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author chencheng0816@gmail.com
 */
@Component
public class SequenceNoGeneratorImpl implements SequenceNoGenerator {

    private static final String SEQUENCE_NO_PAY_PREFIX = "pay:seqno:";
    private static final String SEQUENCE_NO_ORDER_PREFIX = "order:seqno:";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String generate(String businessPrefix, String sequenceNoType) {
        if (StringUtils.isBlank(businessPrefix)) {
            throw new IllegalArgumentException("businessPrefix should not be null");
        }
        String currentTime = DateUtils.formatDate(new Date(), DateUtils.YYMMDDHHMMSS);
        String key = sequenceNoType + businessPrefix + currentTime;
        Long index = redisTemplate.opsForValue().increment(key, 1);
        if (index == 1) {
            // 集群机器之间不允许出现超过5分钟的时间差
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        }

        String indexStr = String.format("%04d", index);
        //生成10到90之间的随机数
        int randomStr = (int)(Math.random() * 90) + 10;
        return businessPrefix + currentTime + indexStr + randomStr;
    }

    @Override
    public String getOrderNo(String orderType) {
        //交支付单号生成规则: 商品类型(A, B) + 当前环境标识(1, 3, 6) + YYMMDDHHMMSS + 四位数字 + 两位随机数字;
        String preFix = orderType + getEnvMark();
        return generate(preFix, SEQUENCE_NO_ORDER_PREFIX);
    }

    @Override
    public String getOutTradeNo(PayChannelEnums payChannel) {
        //交支付单号生成规则: 当前环境标识(1, 3, 6) + 支付类型 + YYMMDDHHMMSS + 四位数字 + 两位随机数字;
        String preFix = getEnvMark() + getPayChannelMark(payChannel);
        return generate(preFix, SEQUENCE_NO_PAY_PREFIX);
    }

    /**
     * 获取当前环境标识
     * @return
     */
    private String getEnvMark() {
        return DEV_PREFIX;
    }

    /**
     * 获取支付渠道标识
     * @param payChannel
     * @return
     */
    private String getPayChannelMark(PayChannelEnums payChannel) {
        String payChannelMark;
        switch (payChannel) {
            case WX:
                payChannelMark = WX_PAY_PREFIX;
                break;
            case ALIPAY:
                payChannelMark = ALIPAY_PREFIX;
                break;
            default:
                payChannelMark = "5";
        }
        return payChannelMark;
    }
}
