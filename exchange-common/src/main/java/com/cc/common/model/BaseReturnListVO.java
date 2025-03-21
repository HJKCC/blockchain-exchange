package com.cc.common.model;

import java.util.ArrayList;
import java.util.List;

public class BaseReturnListVO {
	private String returnCode;
	private List<AlipayRespInfo> alipayRespInfos = new ArrayList<>();
	private int size = 0;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public List<AlipayRespInfo> getAlipayRespInfos() {
		return alipayRespInfos;
	}

	public void setAlipayRespInfos(List<AlipayRespInfo> alipayRespInfos) {
		this.alipayRespInfos = alipayRespInfos;
		this.size = alipayRespInfos.size();
	}
	
	public int getSize() {
		return size;
	}
	
	public void addBaseReturnInfoVO(AlipayRespInfo alipayRespInfo) {
		this.alipayRespInfos.add(alipayRespInfo);
		size++;
	}
}
