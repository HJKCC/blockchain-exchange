package com.cc.api;

import com.cc.model.ProductOrderDO;
import com.cc.model.ProductDO;
import com.cc.model.UserDO;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/8/10 17:07
 * @Description OrderService
 */
public interface ProductOrderService {

	ProductOrderDO findById(Long id);

	List<ProductOrderDO> findAllOrders();

	ProductOrderDO addOrder(List<ProductDO> productList, UserDO userDO );

	int modifyOrder(ProductOrderDO record);

	int deleteOrder(String ids);

	Object pay(ProductOrderDO productOrderDO, UserDO userDO);
}
