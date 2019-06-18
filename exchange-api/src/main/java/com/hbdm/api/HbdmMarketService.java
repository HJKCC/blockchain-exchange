package com.hbdm.api;

import com.cc.common.huobi.model.HuobiListResult;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/16 15:15
 * @Description HbdmMarketService
 */
public interface HbdmMarketService {
	HuobiListResult getKline(String symbol, String period, Integer size);
}
