package com.cc.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
  
public class ShiroSessionListener implements SessionListener {  
  
    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);  
  
    @Autowired  
    private ShiroSessionDAO sessionDAO;
  
    @Override  
    public void onStart(Session session) {  
        // 会话创建时触发  
        logger.info("ShiroSessionListener session {} 被创建", session.getId());  
    }  
  
    @Override  
    public void onStop(Session session) {  
        sessionDAO.delete(session);  
        // 会话被停止时触发  
        logger.info("ShiroSessionListener session {} 被销毁", session.getId());  
    }  
  
    @Override  
    public void onExpiration(Session session) {  
        sessionDAO.delete(session);  
        //会话过期时触发  
        logger.info("ShiroSessionListener session {} 过期", session.getId());  
    }  
}  
