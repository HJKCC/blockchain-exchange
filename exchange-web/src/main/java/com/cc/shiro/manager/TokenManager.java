package com.cc.shiro.manager;

import com.cc.model.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;

/**
 * Shiro管理下的Token工具类
 */
public class TokenManager {
	/**
	 * 获取当前登录的用户User对象
	 * @return
	 */
	public static UserDO getToken(){
		UserDO userDO = (UserDO)SecurityUtils.getSubject().getPrincipal();
		return userDO;
	}
	
	/**
	 * 获取当前用户的Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	/**
	 * 获取当前用户NAME
	 * @return
	 */
	public static String getUsername(){
		return getToken().getName();
	}
	/**
	 * 获取当前用户ID
	 * @return
	 */
	public static String getUserId(){
		return getToken()==null?null:getToken().getName();
	}
	/**
	 * 把值放入到当前登录用户的Session里
	 * @param key
	 * @param value
	 */
	public static void setVal2Session(Object key ,Object value){
		getSession().setAttribute(key, value);
	}
	/**
	 * 从当前登录用户的Session里取值
	 * @param key
	 * @return
	 */
	public static Object getVal2Session(Object key){
		return getSession().getAttribute(key);
	}
	/**
	 * 获取验证码，获取一次后删除
	 * @return
	 */
	public static String getYZM(){
		String code = (String) getSession().getAttribute("CODE");
		getSession().removeAttribute("CODE");
		return code ;
	}
	
	/**
	 * 登录
	 * @return
	 */
	public static UserDO login(UsernamePasswordToken token){
		SecurityUtils.getSubject().login(token);
		return getToken();
	}

	/**
	 * 判断是否登录
	 * @return
	 */
	public static boolean isLogin() {
		return null != SecurityUtils.getSubject().getPrincipal();
	}
	/**
	 * 退出登录
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
}
