package com.hbdm.api;

import com.cc.common.huobi.model.HuobiListResult;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/21 15:54
 * @Description HbdmV1Service
 */
public interface HbdmV1Service {
	HuobiListResult getContractInfo(String symbol, String contractType, String contractCode);
}
