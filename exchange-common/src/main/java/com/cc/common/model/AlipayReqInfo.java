package com.cc.common.model;

/**
 * 阿里支付 公共请求参数
 */
public class AlipayReqInfo {

	/**
	 * 支付宝分配给开发者的应用ID（必填）
	 */
	private String appId;
	/**
	 * 接口名称（必填）
	 */
	private String method;
	/**
	 * 仅支持JSON（可空）
	 */
	private String format;
	/**
	 * 同步返回地址，跳转至商户指定的同步通知页面，HTTP/HTTPS开头字符串（可空）
	 */
	private String returnUrl;
	/**
	 * 请求使用的编码格式，如utf-8,gbk,gb2312等（必填）
	 */
	private String charset;
	/**
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2，sign_type（必填）
	 */
	private String signType;
	/**
	 * 商户请求参数的签名串（必填）
	 */
	private String sign;
	/**
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"（必填）
	 */
	private String timestamp;
	/**
	 * 调用的接口版本，固定为：1.0（必填）
	 */
	private String version;
	/**
	 * 异步后台通知的，处理支付成功后的业务逻辑，http/https路径。支付宝会发多次，直到收到你的响应（可空）
	 */
	private String notifyUrl;
	/**
	 * 授权信息（可空）
	 */
	private String appAuthToken;
	/**
	 * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档（必填）
	 */
	private String bizContent;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAppAuthToken() {
		return appAuthToken;
	}

	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
	}

	public String getBizContent() {
		return bizContent;
	}

	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}
}
