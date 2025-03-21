package com.cc.common.notify;

import com.cc.common.enums.PayChannelEnums;
import com.cc.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/12/18 19:47
 * @Description ThirdChannelNotifyParams
 */
public class AlipayTradePagePayNotify {
	/**
	 * 支付渠道
	 */
	private PayChannelEnums payChannel;

	/**
	 * 支付单号
	 */
	private String tradeNo;

	/**
	 * 第三方交易单号
	 */
	private String outTradeNo;

	/**
	 * 通知金额
	 */
	private BigDecimal fee;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 支付时间
	 */
	private Date payTime;

	/**
	 * 通知code
	 */
	private String code;

	/**
	 * 通知信息
	 */
	private String msg;

	public PayChannelEnums getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(PayChannelEnums payChannel) {
		this.payChannel = payChannel;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	/**
	 * 组装通知参数
	 * @param notifyData
	 * @return
	 */
	private AlipayTradePagePayNotify assembleNotifyParam(Map<String, String> notifyData) {
		AlipayTradePagePayNotify param = new AlipayTradePagePayNotify();
		param.setPayChannel(PayChannelEnums.ALIPAY);
		param.setTradeNo(notifyData.get("out_trade_no"));
		param.setOutTradeNo(notifyData.get("trade_no"));
		param.setSign(notifyData.get("sign"));

		String totalAmount = notifyData.get("total_amount");
		if (StringUtils.isNotBlank(totalAmount)) {
			param.setFee(new BigDecimal(totalAmount));
		}

		String gmtPayment = notifyData.get("gmt_payment");
		if(StringUtils.isNotBlank(gmtPayment)) {
			Date payTime = DateUtils.toDate(gmtPayment, DateUtils.DEFAULT_DATETIME_FORMAT);
			param.setPayTime(payTime);
		}

		return param;
	}
}
