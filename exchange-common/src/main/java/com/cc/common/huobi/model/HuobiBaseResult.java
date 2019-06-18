package com.cc.common.huobi.model;

import java.io.Serializable;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/13 17:36
 * @Description HuobiBaseResult
 */
public class HuobiBaseResult implements Serializable {
	/**
	 * 请求处理结果，"ok" 或"error"
	 */
	private String status;
	/**
	 * 响应生成时间点，单位：毫秒
	 */
	private String ts;
	/**
	 * 错误码，默认ok
	 */
	private int errCode = 200;
	/**
	 * 错误信息
	 */
	private String errMsg = "";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
