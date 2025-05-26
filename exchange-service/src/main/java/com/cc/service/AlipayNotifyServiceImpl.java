package com.cc.service;

import com.cc.api.AlipayNotifyService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/12/18 13:52
 * @Description AlipayNotifyServiceImpl
 */
@Service("alipayNotifyService")
public class AlipayNotifyServiceImpl implements AlipayNotifyService {
	@Override
	public boolean payNotifyHandle(Map<String,String> notifyData) {
		return false;
	}
	
}
