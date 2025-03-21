package com.cc.controller;

import com.cc.api.ProductService;
import com.cc.common.controller.BaseController;
import com.cc.model.ProductDO;
import com.cc.shiro.session.ShiroSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/7/30 10:42
 * @Description 商品操作类
 */
@Transactional
@Controller
@RequestMapping("product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ShiroSessionDAO shiroSessionDAO;

	@ResponseBody
	@RequestMapping("list")
	public String list() {
		initResultMap();

		list = productService.findAllProducts();

		return jsonList();
	}
}
