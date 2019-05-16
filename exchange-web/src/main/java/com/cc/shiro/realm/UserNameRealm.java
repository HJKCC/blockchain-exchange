package com.cc.shiro.realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.cc.api.UserService;
import com.cc.common.util.CommonUtil;
import com.cc.model.UserDO;
import com.cc.shiro.session.ShiroSession;
import com.cc.shiro.session.ShiroSessionDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserNameRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private ShiroSessionDAO shiroSessionDAO;
	
	//获取用户权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		UserDO user = (UserDO) principals.getPrimaryPrincipal();
//		List<String> permissionList = purviewService.findPermissionByUserId(user.getId());
		List<String> permissionList = new ArrayList<String>();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(new HashSet(permissionList));
		
		return authorizationInfo;
	}

	//用户登录认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();

		//踢出已登录的用户
		Collection<Session> sessions = shiroSessionDAO.getActiveSessions();
		for (Session session : sessions) {
			if (username.equals(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))) {
				shiroSessionDAO.delete(session);   //清除session
				break;
			}
		}

		UserDO userDO = userService.login(username);
		if (CommonUtil.isEmpty(userDO)) {
			throw new UnknownAccountException("用户不存在！");
		}

		String password = userDO.getPassword();
		String realmName = this.getName();  //当前realm对象的name。调用父类的getName()方法即可
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userDO, password, realmName);

		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
