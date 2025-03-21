package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import com.cc.api.AlipayNotifyService;
import com.cc.api.PayOrderService;
import com.cc.common.alipay.AlipayConfig;
import com.cc.common.controller.BaseController;
import com.cc.common.enums.PayChannelEnums;
import com.cc.common.util.CommonUtil;
import com.cc.common.util.sequence.SequenceNoGenerator;
import com.cc.entity.AlipayPageReqInfo;
import com.cc.model.PayOrderDO;
import com.cc.model.ProductOrderDO;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/9/4 17:33
 * @Description 支付订单操作类
 */
@Transactional
@Controller
@RequestMapping("payOrder")
public class PayOrderController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderController.class);

	private static final String RETURN_SUCCESS = "success";

	private static final String RETURN_FAIL = "fail";

	@Autowired
	private AlipayConfig alipayConfig;
	@Autowired
	private SequenceNoGenerator sequenceNoGenerator;
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private AlipayNotifyService alipayNotifyService;

	/**
	 * 创建支付订单
	 * @param productOrderDO
	 * @return
	 */
	@ResponseBody
	@RequestMapping("create")
	public String create(ProductOrderDO productOrderDO) {
		initResultMap();

		PayOrderDO payOrderDO = payOrderService.selectByOrderNo(productOrderDO.getOrderNo());
		if (CommonUtil.isNotEmpty(payOrderDO)) {
			return jsonObject(payOrderDO);
		}

		String outTradeNo = sequenceNoGenerator.getOutTradeNo(PayChannelEnums.ALIPAY);
		payOrderDO = new PayOrderDO();
		payOrderDO.setOutTradeNo(outTradeNo);
		payOrderDO.setAppId("test");
		payOrderDO.setOrderNo(productOrderDO.getOrderNo());
		payOrderDO.setOrderTime(productOrderDO.getModifyTime());
		payOrderDO.setOrderFee(productOrderDO.getTotalAmount());
//		payOrderDO.payEndTime = orderInfo.getPayEndTime();
//		payOrderDO.orderStatus = OrderStatusEnums.PENDING_PAY;
		object = payOrderService.insert(payOrderDO);

		return jsonObject(payOrderDO);
	}

	/**
	 * 支付
	 * @param payOrderDO
	 * @return
	 */
	@ResponseBody
	@RequestMapping("pay")
	public String pay(PayOrderDO payOrderDO) {
		initResultMap();

		AlipayPageReqInfo alipayPageReqInfo = new AlipayPageReqInfo();
		alipayPageReqInfo.setOut_trade_no(payOrderDO.getOutTradeNo());
		alipayPageReqInfo.setTotal_amount(payOrderDO.getOrderFee());
		alipayPageReqInfo.setSubject("test order");
		alipayPageReqInfo.setBody("测试订单");

		String res = payOrderService.pay(alipayPageReqInfo);

		return jsonObject(res);
	}

	/**
	 * 支付异步回调。。
	 * 如果return的不是success, 支付宝会继续调用这个接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "payNotify", method = RequestMethod.POST)
	public String payNotify(HttpServletRequest request) {
		try {
			Map<String,String> notifyData = Maps.newHashMap();
			Map<String, String[]> requestParams = request.getParameterMap();
			LOGGER.info("支付宝支付结果通知参数:{}", JSON.toJSONString(requestParams));
			for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
				String name = entry.getKey();
				String[] values = entry.getValue();
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				notifyData.put(name, valueStr);
			}

			if (notifyData.size() < 1) {
				LOGGER.error("支付宝支付支付结果通知数据解析为空");
				return RETURN_FAIL;
			}

			boolean signVerified = AlipaySignature.rsaCheckV1(notifyData, alipayConfig.getPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名
			if(!signVerified){
				LOGGER.error("支付宝支付结果通知验证签名失败");
				return RETURN_FAIL;
			}

			boolean flag = alipayNotifyService.payNotifyHandle(notifyData);
			if (!flag) {
				return RETURN_FAIL;
			}
			LOGGER.info("支付宝支付结果通知返回处理成功");
			return RETURN_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("支付宝支付结果通知处理异常:", e);
			return RETURN_FAIL;
		}
	}
}
