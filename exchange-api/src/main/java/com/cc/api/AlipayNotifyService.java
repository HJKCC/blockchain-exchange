package com.cc.api;

import java.util.Map;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/12/18 11:52
 * @Description AlipayNotifyService 支付宝通知业务处理
 */
public interface AlipayNotifyService {
	/**
	 * 支付通知处理
	 * @param notifyData 通知数据，做业务逻辑使用
	 * @return
	 */
	boolean payNotifyHandle(Map<String,String> notifyData);
}
