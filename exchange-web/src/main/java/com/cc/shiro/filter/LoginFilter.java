package com.cc.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cc.common.constant.ExchangeCST;
import com.cc.common.util.GsonUtil;
import com.cc.model.UserDO;
import org.apache.shiro.web.filter.AccessControlFilter;
import com.cc.shiro.manager.TokenManager;

import java.util.HashMap;
import java.util.Map;

public class LoginFilter  extends AccessControlFilter {
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Boolean flag = Boolean.FALSE;
		
		UserDO token = TokenManager.getToken();
		if(null != token || isLoginRequest(request, response)){// && isEnabled()
			flag = Boolean.TRUE;
        }
		
		return flag;
            
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put(ExchangeCST.RESULT_CODE, "6");
		resultMap.put(ExchangeCST.RESULT_INFO, "用户登录信息已失效，请重新登录！");
		response.getWriter().write(GsonUtil.toJson(resultMap));

		//保存Request和Response 到登录后的链接
//		saveRequestAndRedirectToLogin(request, response);
		return Boolean.FALSE ;
	}
}
