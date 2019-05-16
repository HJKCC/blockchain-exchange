package com.cc.controller;

import com.cc.api.UserService;
import com.cc.common.constant.ExchangeCST;
import com.cc.common.controller.BaseController;
import com.cc.shiro.manager.TokenManager;
import com.cc.shiro.session.ShiroSessionDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/5/4 16:37
 * @Description LoginController
 */
@Transactional
@Controller
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ShiroSessionDAO shiroSessionDAO;

	@RequestMapping("login")
	@ResponseBody
	public String login(String username, String password) {
		initResultMap();

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			// 身份验证
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			resultMap.put(ExchangeCST.RESULT_INFO, "登录成功！");
			logger.info("用户id {} 登录成功", username);
		} catch (UnknownAccountException e) {
			// 验证失败
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "输入用户名或密码错误！");
			logger.info("用户名错误或者不存在");
		} catch (IncorrectCredentialsException e){
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "输入用户名或密码错误！");
			logger.info("密码不匹配");
		} catch (LockedAccountException e){
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "用户已被锁定，请联系管理员！");
			logger.info("用户已被锁定，请联系管理员");
		} catch (DisabledAccountException e){
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "用户已被禁用，请联系管理员！");
			logger.info("用户已被禁用，请联系管理员");
		} catch (ExcessiveAttemptsException e){
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "用户登录次数过多，请稍后再试！");
			logger.info("用户登录次数过多");
		} catch (AuthenticationException  e){
			resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.ERROR);
			resultMap.put(ExchangeCST.RESULT_INFO, "用户登录失败，请稍后再试！");
			logger.info("用户登录失败，请联系管理员");
		}

		return jsonRes();
	}

	@RequestMapping("logout")
	@ResponseBody
	public String logout() {
		initResultMap();
		String username = TokenManager.getUsername();

		TokenManager.logout();
		logger.info("用户id {} 已成功退出登录！", username);

		return jsonRes();
	}
}
