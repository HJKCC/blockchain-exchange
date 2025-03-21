package com.cc.service;

import com.alibaba.fastjson.TypeReference;
import com.cc.common.huobi.HuobiApiRequestImpl;
import com.cc.common.huobi.model.ContractVO;
import com.cc.common.huobi.model.HuobiListResult;
import com.cc.common.huobi.model.RequestAndJsonParser;
import com.cc.common.huobi.util.UrlParamsBuilder;
import com.cc.common.util.CommonUtil;
import com.cc.common.util.FastJsonUtil;
import com.cc.entity.AlipayPageReqInfo;
import com.hbdm.api.HbdmV1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/21 16:03
 * @Description HbdmV1ServiceImpl
 */
@Service("hbdmV1Service")
public class HbdmV1ServiceImpl implements HbdmV1Service {
	@Autowired
	private HuobiApiRequestImpl huobiApiRequestImpl;

	@Override
	public HuobiListResult getContractInfo(String symbol, String contractType, String contractCode) {
		RequestAndJsonParser<HuobiListResult<ContractVO>> requestAndJsonParser = new RequestAndJsonParser<>();
		UrlParamsBuilder builder = UrlParamsBuilder.build();
		if (CommonUtil.isNotEmpty(symbol)) {
			builder.putToUrl("symbol", symbol);
		}
		if (CommonUtil.isNotEmpty(contractType)) {
			builder.putToUrl("contract_type", contractType);
		}
		if (CommonUtil.isNotEmpty(contractCode)) {
			builder.putToUrl("contract_code", contractCode);
		}
		requestAndJsonParser.request = huobiApiRequestImpl.createRequestByGet("/api/v1/contract_contract_info", builder);

		requestAndJsonParser.jsonParser = (str -> {
			return FastJsonUtil.fromJson(str, new TypeReference<HuobiListResult<ContractVO>>(){});
		});

		return huobiApiRequestImpl.callSync(requestAndJsonParser);
	}
}
