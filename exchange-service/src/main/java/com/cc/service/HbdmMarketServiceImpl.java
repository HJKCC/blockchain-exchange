package com.cc.service;

import com.alibaba.fastjson.JSON;
import com.cc.common.huobi.*;
import com.cc.common.huobi.model.HuobiListResult;
import com.cc.common.huobi.model.KLineVO;
import com.cc.common.huobi.model.RequestAndJsonParser;
import com.cc.common.huobi.util.InputChecker;
import com.cc.common.huobi.util.UrlParamsBuilder;
import com.cc.common.util.FastJsonUtil;
import com.alibaba.fastjson.TypeReference;
import com.hbdm.api.HbdmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/16 15:43
 * @Description HbdmMarketServiceImpl
 */
@Service("hbdmMarketService")
public class HbdmMarketServiceImpl implements HbdmMarketService {
	@Autowired
	private HuobiApiRequestImpl huobiApiRequestImpl;

	@Override
	public HuobiListResult getKline(String symbol, String period, Integer size) {
		InputChecker.checker()
				.checkNull(symbol)
				.checkRange(size, 1, 2000, "size")
				.shouldNotNull(period, "period");
		RequestAndJsonParser<HuobiListResult<KLineVO>> requestAndJsonParser = new RequestAndJsonParser<>();
		UrlParamsBuilder builder = UrlParamsBuilder.build()
				.putToUrl("symbol", symbol)
				.putToUrl("period", period)
				.putToUrl("size", size);
		requestAndJsonParser.request = huobiApiRequestImpl.createRequestByGet("/market/history/kline", builder);

		requestAndJsonParser.jsonParser = (str -> {
			return FastJsonUtil.fromJson(str, new TypeReference<HuobiListResult<KLineVO>>(){});
		});

		return huobiApiRequestImpl.callSync(requestAndJsonParser);
	}
}
