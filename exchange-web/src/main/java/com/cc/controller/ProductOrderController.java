package com.cc.controller;

import com.cc.api.ProductOrderService;
import com.cc.common.controller.BaseController;
import com.cc.model.ProductDO;
import com.cc.model.UserDO;
import com.cc.shiro.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/8/10 17:01
 * @Description 商品订单操作类
 */
@Transactional
@Controller
@RequestMapping("productOrder")
public class ProductOrderController extends BaseController {
	@Autowired
	private ProductOrderService productOrderService;

	/**
	 * 提交订单
	 * @param productList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public String add(@RequestBody List<ProductDO> productList) {

		initResultMap();

		UserDO userDO = TokenManager.getToken();
		object = productOrderService.addOrder(productList, userDO);

		return jsonObject(object);
	}

}
