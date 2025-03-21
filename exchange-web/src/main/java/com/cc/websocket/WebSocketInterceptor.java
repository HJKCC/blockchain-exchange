package com.cc.websocket;

import com.cc.shiro.manager.TokenManager;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author chencheng0816@gmail.com
 * @date 2020/4/22 20:01
 * @Description WebSocketInterceptor
 */
public class WebSocketInterceptor implements HandshakeInterceptor {
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			if (TokenManager.isLogin()) {
				map.put(TokenManager.getUsername(), TokenManager.getToken());
			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

	}
}
