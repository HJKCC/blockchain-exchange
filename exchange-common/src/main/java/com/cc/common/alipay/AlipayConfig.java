package com.cc.common.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 应用启动加载文件
 * @author chencheng0816@gmail.com
 * @date 2019/7/10 15:42
 * @Description AlipayConfig
 */
@Component
@PropertySource(value = {"classpath:properties/alipay.properties"})
public class AlipayConfig {

	@Value("${appId}")
	private String appId;
	@Value("${privateKey}")
	private String privateKey;
	@Value("${publicKey}")
	private String publicKey;
	@Value("${notifyUrl}")
	private String notifyUrl;
	@Value("${returnUrl}")
	private String returnUrl;
	@Value("${signType}")
	private String signType;
	@Value("${charset}")
	private String charset;
	@Value("${gatewayUrl}")
	private String gatewayUrl;
	@Value("${logPath}")
	private String logPath;
	@Value("${format}")
	private String format;

	/**
	 * 获取配置参数值
	 */
	public String getAppId() {
		return appId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public String getSignType() {
		return signType;
	}

	public String getCharset() {
		return charset;
	}

	public String getGatewayUrl() {
		return gatewayUrl;
	}

	public String getLogPath() {
		return logPath;
	}

	public String getFormat() {
		return format;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
