package com.cc.service;

import com.cc.api.ProductService;
import com.cc.dao.ProductDAO;
import com.cc.model.ProductDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/7/30 10:48
 * @Description ProductServiceImpl
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<ProductDO> findAllProducts() {
		return productDAO.selectAllProducts();
	}

	@Override
	public void addProduct(ProductDO ProductDO) {

	}

	@Override
	public void modifyProduct(ProductDO ProductDO) {

	}

	@Override
	public void deleteProduct(String ids) {

	}
}
