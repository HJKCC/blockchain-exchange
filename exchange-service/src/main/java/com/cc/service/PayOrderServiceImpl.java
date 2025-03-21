package com.cc.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.cc.api.PayOrderService;
import com.cc.common.alipay.AlipayConfig;
import com.cc.entity.AlipayPageReqInfo;
import com.cc.model.PayOrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/9/4 17:40
 * @Description PayOrderServiceImpl
 */
@Service("payOrderService")
public class PayOrderServiceImpl implements PayOrderService {
	@Autowired
	private AlipayConfig alipayConfig;

	@Override
	public int insert(PayOrderDO payOrderDO) {
		return 0;
	}

	@Override
	public PayOrderDO selectByOrderNo(String orderNo) {
		String appId = alipayConfig.getAppId();
		return null;
	}

	@Override
	public PayOrderDO selectByPayNo(String payNo) {
		return null;
	}

	@Override
	public int orderClose(String payNo) {
		return 0;
	}

	@Override
	public int updatePayRecord(PayOrderDO payOrderDO) {
		return 0;
	}

	@Override
	public int updatePayResult(PayOrderDO payOrderDO) {
		return 0;
	}

	@Override
	public List<PayOrderDO> listPaySuccessBySettleDate(String settleDate) {
		return null;
	}

	@Override
	public List<Long> listPayAccountIdBySettleDate(String settleDate) {
		return null;
	}

	@Override
	public int closeTimeOutOrder() {
		return 0;
	}

	@Override
	public String pay(AlipayPageReqInfo alipayPageReqInfo) {
		// 1、获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getPrivateKey(), alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getPublicKey(), alipayConfig.getSignType());
		// 2、设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 页面跳转同步通知页面路径
		alipayRequest.setReturnUrl(alipayConfig.getReturnUrl() + "/about");
		// 服务器异步通知页面路径
		alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl() + "/payOrder/payNotice.action");
		// 封装参数
//		alipayRequest.setBizContent(JSON.toJSONString(alipayPageReqInfo));
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ alipayPageReqInfo.getOut_trade_no() +"\","
				+ "\"total_amount\":\""+ alipayPageReqInfo.getTotal_amount() +"\","
				+ "\"subject\":\""+ alipayPageReqInfo.getSubject() +"\","
				+ "\"body\":\""+ alipayPageReqInfo.getBody() +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 3、请求支付宝进行付款，并获取支付结果
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		// 返回付款信息
		return result;
	}

	public String payTest(AlipayPageReqInfo alipayPageReqInfo1) {
		AlipayConfig alipayConfigTest = new AlipayConfig();
		alipayConfigTest.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfx7MCS2d4YqBi2yy4cXfVyt4B75snOG4tgBBAgOJ5IoTjI3AdAdV/waVN3qvzgx1j8aO/H/t4Mavo435hbCmd0ZDRvwRp3u2+WFXGeC+fjzTgZxCwOUKEcnWNYJJogj3zXsge1Gfr8P5s/JT+ox92X5t0JJPWiiHLFTECkVMq0WZJq4AGhtxOH24HKKzR5CC4NZjKOVaUjt1r6cfFkPI2pVzq2ViFbji8g3EvMz9glVXaKEoRT/GZqyHUcXoRAzU7T8fZFGBS7qWURV9+a1GgU5Es8Cl5mqafIKKdoznQnjsmQgZb+GIzRNjEL+k/ePQ7q0qOovpRbrOMfbwH9ybLAgMBAAECggEASwEWILjBT3pPI5MupAt0DPZX/vIeYMDrPNjlmZkZT1ygibkBBNfuhUmUbEsr+wyBN1Rwe8juclTDt+U4INMuB7WMAqExi4Kq0NeuuDzkSWioYmZpE4e9ocLzX5V0vQ6aUQ39qUesJUq7HDB0h/yIuRZGdmEkJA5cUUMjECdBZuH0Ilq88/FIENNJ7zz+VBnm7ASBmWychy4tuU2FqlV8XgzNmbSaHHyb6kNZuZCh5bEEHXyc0kL2Qi7h7X7VJW4ZyS9DvkHu7dq9PZcPj+8T41ae+16cpib1FOlKw6nBb7kZbc9LFYYbWDzEGgMPxZSoSVbxorIEwwI6/TaWm235sQKBgQDvkhl+W7jNmtYmnzexjw8PlhY59a1buetkIwtREY98PiQdFLUQTp3Ya//slFaAwYa2aVnyEWi2KP1+8c6oXQW399CBGNDp55hCKbdpmGSRsGKLyNQ5lXTYjoczIjxaKVBq1SSbEZNCGfXKh842lvSH/j5etKbdDwUYhcFgHryZfQKBgQDvIGEuyPa1MYixdGaURS8M0nW72zXsr0yjXOxeIyzOkpvTThYQ3dARi0id7k2uiNVPEPO0Ptbi/k/vDcuCPZE0vDNxM6s+G5oiVZixRBfQV38Pk7vPw0lz9GorSvXQOq3PHhVp7d5kBDyFbYC/ZpJi5KbDIqRo6Fv4UH1VkiPz5wKBgQDU1oevqu1UPOtlBK8GIILQ5hZ5B7ssctCd9kRnkaJYTxzmeS8jfbTeQZTtKhxv5383MAGo/a55p5EO5uUizwH1n0SubM2uAQNURJueRLWUeZATgCiLwCw++OFEnHXh5DJ9tz7+RW7+mvGzLVQXS6WT7Fyc5wIYAvAUCUDzlFsaQQKBgFZFPEqAvmEIieRbtlZvz1W06DzqDT5i0Cdv8ubTkerFAeruFQ/otpRdx8kSnHwHac2otAOnWCd00KnSNEW8qJJ6A6UnmvRBSQ5yCzCI/H5x0qlwByYhsnJfD56m4KBLS0GZ/OfGpaE31g3dP8HN46pCC9gCu4YNroCbqVwh6U6pAoGAD7FF+c+H+lDiaC0JqzuB+Ki1fiGwJNWVBvUkYkdUeKodospheAu1ocIVr3Lm71IHFX6oKqRDOQoDAF86NP1cMlP9i5DIEqLWEws79tfb6+djqezsu6/R7WRbESL6Z0k0TImdvJ+43ePDvsL1hj4w9awuaGAAvYLNfLl8SsTkEmw=");
		alipayConfigTest.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA38ezAktneGKgYtssuHF31creAe+bJzhuLYAQQIDieSKE4yNwHQHVf8GlTd6r84MdY/Gjvx/7eDGr6ON+YWwpndGQ0b8Ead7tvlhVxngvn4804GcQsDlChHJ1jWCSaII9817IHtRn6/D+bPyU/qMfdl+bdCST1oohyxUxApFTKtFmSauABobcTh9uByis0eQguDWYyjlWlI7da+nHxZDyNqVc6tlYhW44vINxLzM/YJVV2ihKEU/xmash1HF6EQM1O0/H2RRgUu6llEVffmtRoFORLPApeZqmnyCinaM50J47JkIGW/hiM0TYxC/pP3j0O6tKjqL6UW6zjH28B/cmywIDAQAB");
		alipayConfigTest.setGatewayUrl("https://openapi.alipaydev.com/gateway.do");
		alipayConfigTest.setAppId("2016101100656875");
		alipayConfigTest.setFormat("JSON");
		alipayConfigTest.setCharset("utf-8");
		alipayConfigTest.setSignType("RSA2");
		alipayConfigTest.setReturnUrl("http://192.168.10.106:8080/error.html");
		alipayConfigTest.setNotifyUrl("http://192.168.10.106:8080/sccess.html");

		// 1、获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfigTest.getGatewayUrl(), alipayConfigTest.getAppId(), alipayConfigTest.getPrivateKey(), alipayConfigTest.getFormat(), alipayConfigTest.getCharset(), alipayConfigTest.getPublicKey(), alipayConfigTest.getSignType());
		// 2、设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 页面跳转同步通知页面路径
		alipayRequest.setReturnUrl(alipayConfigTest.getReturnUrl());
		// 服务器异步通知页面路径
		alipayRequest.setNotifyUrl(alipayConfigTest.getNotifyUrl());

		AlipayPageReqInfo alipayPageReqInfo = new AlipayPageReqInfo();
		alipayPageReqInfo.setOut_trade_no("A1191206091230000140");
		alipayPageReqInfo.setTotal_amount(new BigDecimal(99.99));
		alipayPageReqInfo.setSubject("test order");
		alipayPageReqInfo.setBody("测试订单");
		// 封装参数
//		alipayRequest.setBizContent(JSON.toJSONString(alipayPageReqInfo));
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ "A1191206091230000140" +"\","
				+ "\"total_amount\":\""+ 999 +"\","
				+ "\"subject\":\""+ "test order" +"\","
				+ "\"body\":\""+ "测试订单" +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 3、请求支付宝进行付款，并获取支付结果
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		// 返回付款信息
		return result;
	}
}
