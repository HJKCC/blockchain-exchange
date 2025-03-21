package com.cc.service;

import com.cc.api.ProductAndOrderService;
import com.cc.dao.ProductAndOrderDAO;
import com.cc.model.ProductAndOrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/8/10 18:39
 * @Description ProductAndOrderServiceImpl
 */
@Service("productAndOrderService")
public class ProductAndOrderServiceImpl implements ProductAndOrderService {
	@Autowired
	private ProductAndOrderDAO productAndOrderDAO;

	@Override
	public List<ProductAndOrderDO> findByOrderId() {
		return null;
	}

	@Override
	public List<ProductAndOrderDO> findAllProductAndOrders() {
		return null;
	}

	@Override
	public void add(List<ProductAndOrderDO> productAndOrderDOs) {
		for (ProductAndOrderDO productAndOrderDO : productAndOrderDOs) {
			productAndOrderDAO.insert(productAndOrderDO);
		}
	}

	@Override
	public void deleteByOrderId(String orderIds) {

	}
}
