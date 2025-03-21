package com.cc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cc.common.constant.ExchangeCST;
import com.cc.common.controller.BaseController;
import com.cc.common.huobi.model.ContractVO;
import com.cc.common.huobi.model.HuobiListResult;
import com.cc.common.huobi.model.KLineVO;
import com.cc.common.util.CommonUtil;
import com.hbdm.api.HbdmV1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/21 15:35
 * @Description HbdmV1Controller
 */
@Transactional
@Controller
@RequestMapping("api/v1")
public class HbdmV1Controller extends BaseController {
	@Autowired
	private HbdmV1Service hbdmV1Service;

	@ResponseBody
	@RequestMapping("contractInfo")
	public String contractInfo(String symbol, String contractType, String contractCode) {
		initResultMap();

		HuobiListResult<ContractVO> result = new HuobiListResult<>();
		if (ExchangeCST.ENV_DEV) {
			String resStr = "{\"data\":[{\"symbol\":\"BTC\",\"contract_code\":\"BTC250321\",\"contract_type\":\"this_week\",\"contract_size\":100.000000000000000000,\"price_tick\":0.010000000000000000,\"delivery_date\":\"20250321\",\"delivery_time\":\"1742544000000\",\"create_date\":\"20250307\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"BTC\",\"contract_code\":\"BTC250328\",\"contract_type\":\"next_week\",\"contract_size\":100.000000000000000000,\"price_tick\":0.010000000000000000,\"delivery_date\":\"20250328\",\"delivery_time\":\"1743148800000\",\"create_date\":\"20241213\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"BTC\",\"contract_code\":\"BTC250627\",\"contract_type\":\"quarter\",\"contract_size\":100.000000000000000000,\"price_tick\":0.010000000000000000,\"delivery_date\":\"20250627\",\"delivery_time\":\"1751011200000\",\"create_date\":\"20250314\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"ETH\",\"contract_code\":\"ETH250321\",\"contract_type\":\"this_week\",\"contract_size\":10.000000000000000000,\"price_tick\":0.001000000000000000,\"delivery_date\":\"20250321\",\"delivery_time\":\"1742544000000\",\"create_date\":\"20250307\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"ETH\",\"contract_code\":\"ETH250328\",\"contract_type\":\"next_week\",\"contract_size\":10.000000000000000000,\"price_tick\":0.001000000000000000,\"delivery_date\":\"20250328\",\"delivery_time\":\"1743148800000\",\"create_date\":\"20241213\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"ETH\",\"contract_code\":\"ETH250627\",\"contract_type\":\"quarter\",\"contract_size\":10.000000000000000000,\"price_tick\":0.001000000000000000,\"delivery_date\":\"20250627\",\"delivery_time\":\"1751011200000\",\"create_date\":\"20250314\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"TRX\",\"contract_code\":\"TRX250321\",\"contract_type\":\"this_week\",\"contract_size\":10.000000000000000000,\"price_tick\":0.000010000000000000,\"delivery_date\":\"20250321\",\"delivery_time\":\"1742544000000\",\"create_date\":\"20250307\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"},{\"symbol\":\"TRX\",\"contract_code\":\"TRX250328\",\"contract_type\":\"next_week\",\"contract_size\":10.000000000000000000,\"price_tick\":0.000010000000000000,\"delivery_date\":\"20250328\",\"delivery_time\":\"1743148800000\",\"create_date\":\"20250314\",\"contract_status\":1,\"settlement_time\":\"1742544000000\"}]}";
			JSONObject jsonObject = JSONObject.parseObject(resStr);
// 解析数据层
			JSONArray dataJSONArray = jsonObject.getJSONArray("data");
			List<ContractVO> contractVOS = JSONArray.parseArray(dataJSONArray.toJSONString(), ContractVO.class);
			result.setData(contractVOS);
		} else {
			result = hbdmV1Service.getContractInfo(symbol, contractType, contractCode);
		}


		if (CommonUtil.isEmpty(result)) {
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "火币获取合约信息接口调用失败，请稍候再试");
			return jsonRes();
		} else if (ExchangeCST.STATUS_ERROR.equals(result.getStatus())) {
			resultMap.put(ExchangeCST.RESULT_CODE, result.getErrCode());
			resultMap.put(ExchangeCST.RESULT_INFO, "huobi error: " + result.getErrMsg());
			return jsonRes();
		}


		return jsonList(result.getData());
	}
}
