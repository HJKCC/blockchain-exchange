package com.cc.api;

import com.cc.entity.AlipayPageReqInfo;
import com.cc.model.PayOrderDO;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/9/4 17:36
 * @Description PayOrderService
 */
public interface PayOrderService {
	/**
	 * 插入
	 * @param payOrderDO
	 * @return
	 */
	int insert(PayOrderDO payOrderDO);

	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	PayOrderDO selectByOrderNo(String orderNo);

	/**
	 * 根据预支付单号查询
	 * @param payNo
	 * @return
	 */
	PayOrderDO selectByPayNo(String payNo);

	/**
	 * 订单关闭
	 * @param payNo
	 * @return
	 */
	int orderClose(String payNo);

	/**
	 * 更新支付流水信息
	 * @param payOrderDO
	 * @return
	 */
	int updatePayRecord(PayOrderDO payOrderDO);

	/**
	 * 更新支付结果
	 * @param payOrderDO
	 * @return
	 */
	int updatePayResult(PayOrderDO payOrderDO);


	/**
	 * 查询待支付成功的数据
	 * @param settleDate
	 * @return
	 */
	List<PayOrderDO> listPaySuccessBySettleDate(String settleDate);

	/**
	 * 查询待支付成功的账户
	 * @param settleDate
	 * @return
	 */
	List<Long> listPayAccountIdBySettleDate(String settleDate);

	/**
	 * 关闭超时订单
	 * @return
	 */
	int closeTimeOutOrder();

	String pay(AlipayPageReqInfo alipayPageReqInfo);
}
