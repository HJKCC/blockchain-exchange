package com.hbdm.api;

import com.cc.common.huobi.model.HuobiListResult;
import com.cc.common.huobi.model.HuobiTickResult;
import com.cc.common.huobi.model.KLineVO;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/16 15:15
 * @Description HbdmMarketService
 */
public interface HbdmMarketService {
	/**
	 * 获取k 线数据
	 * @param symbol
	 * @param period
	 * @param size
	 * @return
	 */
	HuobiListResult getKline(String symbol, String period, Integer size);

	/**
	 * 获取聚合行情
	 * @param symbol
	 * @return
	 */
	HuobiTickResult<KLineVO> merged(String symbol);
}
