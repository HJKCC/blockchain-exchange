package com.cc.common.model;

/**
 * 以下返回类型封装类
 * <ID Return="1(0)" status="错误代码">对象ID</ID>
 */
public class BaseReturnInfoVO {

	private String id;
	private String returnCode;
	private String info;
	private String status;
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void initInfo(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public void initInfo(String returnCode, String status) {
		this.returnCode = returnCode;
		this.status = status;
	}
	
	public void initInfo(String returnCode, String status,String mac,String cdid,String serverIp,String pemData) {
		this.returnCode = returnCode;
		this.status = status;
	}

	public void initIpInfo(String returnCode,String type, String desktopIp, String subnetMask,String gateWay, String dns1, String dns2) {
		this.returnCode = returnCode;
	}
}
