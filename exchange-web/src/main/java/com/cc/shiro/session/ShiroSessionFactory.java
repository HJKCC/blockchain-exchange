package com.cc.shiro.session;

import com.cc.common.util.CommonUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;  
import org.apache.shiro.session.mgt.SessionFactory; 
import org.apache.shiro.web.session.mgt.DefaultWebSessionContext;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;  
  
public class ShiroSessionFactory implements SessionFactory {  
    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionFactory.class);  
  
    @Override  
    public Session createSession(SessionContext initData) {  
        ShiroSession session = new ShiroSession();
        HttpServletRequest request = (HttpServletRequest)initData.get(DefaultWebSessionContext.class.getName() + ".SERVLET_REQUEST");  
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session.setHost(CommonUtil.getClientIp(request));
        return session;  
    }
}  