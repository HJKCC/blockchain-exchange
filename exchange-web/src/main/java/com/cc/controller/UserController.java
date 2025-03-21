package com.cc.controller;

import com.cc.api.UserService;
import com.cc.common.constant.ExchangeCST;
import com.cc.common.controller.BaseController;
import com.cc.model.UserDO;
import com.cc.shiro.manager.TokenManager;
import com.cc.shiro.session.ShiroSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/5/14 21:49
 * @Description UserController
 */
@Transactional
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ShiroSessionDAO shiroSessionDAO;

	@ResponseBody
	@RequestMapping("list")
	public String list() {
		initResultMap();

		list = userService.findAllUsers();

		return jsonList();
	}

	@ResponseBody
	@RequestMapping("modifyUser")
	public String modifyUser(UserDO userDO) {
		initResultMap();

		Date now = new Date();
		userDO.setCreatedTime(now);
		userDO.setModifiedTime(now);
		userService.modifyUser(userDO);

		return jsonRes();
	}

	@ResponseBody
	@RequestMapping("deleteUser")
	public String deleteUser(String ids) {
		initResultMap();

		userService.deleteUser(ids);

		return jsonRes();
	}

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
	@RequestMapping("getCurrentUser")
	public String getCurrentUser() {
		initResultMap();

		UserDO userDO = TokenManager.getToken();

		return jsonObject(userDO);
	}


	@ResponseBody
	@RequestMapping("resetPassword")
	public String resetPassword(String oldPassword, String newPassword) {
		initResultMap();

		UserDO userDO = TokenManager.getToken();
		String password = userDO.getPassword();
		if (password.equals(oldPassword)) {
			userDO.setPassword(newPassword);
			userDO.setModifiedTime(new Date());
			userService.modifyUser(userDO);
		} else {
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "输入初始密码有误！");
		}

		return jsonRes();
	}


}
