package com.cc.api;

import com.cc.model.ProductDO;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/7/30 10:48
 * @Description ProductService
 */
public interface ProductService {
	List<ProductDO> findAllProducts();

	void addProduct(ProductDO productDO);

	void modifyProduct(ProductDO productDO);

	void deleteProduct(String ids);
}
