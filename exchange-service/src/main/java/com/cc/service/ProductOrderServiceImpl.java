package com.cc.service;

import com.cc.api.ProductOrderService;
import com.cc.api.ProductAndOrderService;
import com.cc.common.util.sequence.SequenceNoGenerator;
import com.cc.dao.ProductOrderDAO;
import com.cc.dao.ProductDAO;
import com.cc.model.ProductOrderDO;
import com.cc.model.ProductAndOrderDO;
import com.cc.model.ProductDO;
import com.cc.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/8/10 17:16
 * @Description OrderServiceImpl
 */
@Service("productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private SequenceNoGenerator sequenceNoGenerator;
	@Autowired
	private ProductAndOrderService productAndOrderService;
	@Autowired
	private ProductOrderDAO productOrderDAO;
	@Autowired
	private ProductDAO productDAO;

	@Override
	public ProductOrderDO findById(Long id) {
		return null;
	}

	@Override
	public List<ProductOrderDO> findAllOrders() {
		return null;
	}

	@Override
	public ProductOrderDO addOrder(List<ProductDO> productList, UserDO userDO ) {
		String orderNo = sequenceNoGenerator.getOrderNo("A");
		BigDecimal price = new BigDecimal("0.0");
		for (ProductDO productDO : productList) {
			price = price.add(productDO.getPrice());
		}

		// 生成商品订单记录
		ProductOrderDO productOrderDO = new ProductOrderDO();
		productOrderDO.setOrderNo(orderNo);
		productOrderDO.setUserId(userDO.getId());
		productOrderDO.setTotalAmount(price);
		productOrderDAO.insert(productOrderDO);

		// 生成商品-订单对照记录
		List<ProductAndOrderDO> productAndOrderDOs = new ArrayList<>();
		for (ProductDO productDO : productList) {
			ProductAndOrderDO productAndOrderDO = new ProductAndOrderDO();
			productAndOrderDO.setOrderNo(orderNo);
			productAndOrderDO.setProductId(productDO.getId());
			productAndOrderDOs.add(productAndOrderDO);
		}
		productAndOrderService.add(productAndOrderDOs);

		return productOrderDO;
	}

	@Override
	public int modifyOrder(ProductOrderDO record) {
		return 0;
	}

	@Override
	public int deleteOrder(String ids) {
		return 0;
	}

	@Override
	public Object pay(ProductOrderDO productOrderDO, UserDO userDO) {
		return null;
	}
}
