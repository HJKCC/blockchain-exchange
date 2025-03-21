package com.cc.api;

import com.cc.model.ProductAndOrderDO;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/8/10 18:32
 * @Description ProductAndOrderService
 */
public interface ProductAndOrderService {

	List<ProductAndOrderDO> findByOrderId();

	List<ProductAndOrderDO> findAllProductAndOrders();

	void add(List<ProductAndOrderDO> productAndOrderDOs);

	void deleteByOrderId(String orderIds);
}
