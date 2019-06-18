package com.cc.controller;

import com.cc.common.constant.ExchangeCST;
import com.cc.common.controller.BaseController;
import com.cc.common.huobi.model.HuobiListResult;
import com.cc.common.huobi.model.KLineVO;
import com.cc.common.util.CommonUtil;
import com.hbdm.api.HbdmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/6 20:36
 * @Description HbdmMarketController
 */
@Transactional
@Controller
@RequestMapping("hbdm/market")
public class HbdmMarketController extends BaseController {
	@Autowired
	private HbdmMarketService hbdmMarketService;

	@ResponseBody
	@RequestMapping("history/kline")
	public String kline(String symbol, String period, Integer size) {
		initResultMap();

		HuobiListResult<KLineVO> result = hbdmMarketService.getKline(symbol, period, size);

		if (CommonUtil.isEmpty(result)) {
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "火币k线图接口调用失败，请稍候再试");
			return jsonRes();
		} else if (ExchangeCST.STATUS_ERROR.equals(result.getStatus())) {
			resultMap.put(ExchangeCST.RESULT_CODE, result.getErrCode());
			resultMap.put(ExchangeCST.RESULT_INFO, "huobi error: " + result.getErrMsg());
			return jsonRes();
		}

		List<KLineVO> kLineVOList = result.getData();
		total = kLineVOList.size();
		List<String> xAxis = new ArrayList<>();
		List<Long> volume = new ArrayList<>();
		Long[][] kLineData = new Long[total][4];

		int row = 0;
		for (KLineVO kLineVO : kLineVOList) {
			kLineData[row][0] = kLineVO.getOpen();
			kLineData[row][1] = kLineVO.getClose();
			kLineData[row][2] = kLineVO.getLow();
			kLineData[row][3] = kLineVO.getHigh();
			xAxis.add(CommonUtil.formatTimeAxis(kLineVO.getId()));
			volume.add(kLineVO.getVol());
			row++;
		}

		resultMap.put("total", total);
		resultMap.put("xAxis", xAxis);
		resultMap.put("volume", volume);
		resultMap.put("kLineData", kLineData);
		return jsonRes();
	}
}
