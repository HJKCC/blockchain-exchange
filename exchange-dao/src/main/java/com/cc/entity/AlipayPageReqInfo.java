package com.cc.entity;

import com.cc.common.model.AlipayReqInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * alipay.trade.page.pay 请求参数
 * @author chencheng0816@gmail.com
 * @date 2019/11/26 16:39
 * @Description AlipayPageReqInfo
 */
public class AlipayPageReqInfo implements Serializable {
	/**
	 * 商户订单号,64个字符以内（必填）
	 */
	private String out_trade_no;
	/**
	 * 销售产品码，与支付宝签约的产品码名称。目前仅支持FAST_INSTANT_TRADE_PAY（必填）
	 */
	private String productCode = "FAST_INSTANT_TRADE_PAY";
	/**
	 * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]（必填）
	 */
	private BigDecimal total_amount;
	/**
	 * 订单标题（必填）
	 */
	private String subject;
	/**
	 * 订单描述（可选）
	 */
	private String body;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
