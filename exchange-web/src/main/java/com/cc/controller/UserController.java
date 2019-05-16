package com.cc.controller;

import com.cc.api.UserService;
import com.cc.common.controller.BaseController;
import com.cc.model.UserDO;
import com.cc.shiro.session.ShiroSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/5/14 21:49
 * @Description UserController
 */
@Transactional
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ShiroSessionDAO shiroSessionDAO;

	@ResponseBody
	@RequestMapping("register")
	public String register(UserDO userDO) {
		initResultMap();

		Date now = new Date();
		userDO.setCreatedTime(now);
		userDO.setModifiedTime(now);
		userService.addUser(userDO);

		return jsonRes();
	}

	@ResponseBody
	@RequestMapping("list")
	public String list() {
		initResultMap();

		list = userService.findAllUsers();

		return jsonList();
	}
}
