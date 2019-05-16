package com.cc.common.aspect;

import com.cc.common.util.CommonUtil;
import com.cc.common.util.GsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 面向切面编程（自定义日志输出）
 *
 * @author chencheng0816@gmail.com
 * @date 2019/4/29 11:19
 * @Description ExchangeCST
 */
@Component
@Aspect
public class LogAspect {

    /**
     * 日志记录类
     */
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切入点表达式，controller包下所有方法
     */
    @Pointcut("execution(* com.cc.controller.*.*(..))")
    public void controller() {
    }

    /**
     * 环绕通知
     */
	@Around("controller()")
	public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 请求标识
		String uuid = UUID.randomUUID().toString();
		// 请求IP
		String ipAddress = CommonUtil.getClientIp(request);
		// 请求路径
		String requestURL = request.getRequestURL().toString();
		// 请求参数
		String parameters = GsonUtil.toJson(request.getParameterMap());

		log.info("\n\t请求标识: {}\n\t请求IP: {}\n\t请求路径: {}\n\t请求参数: {}", uuid, ipAddress, requestURL, parameters);

		long startTime = System.currentTimeMillis();
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
			long endTime = System.currentTimeMillis();

			log.info("\n\t请求标识: {} \n\t执行时间: {} ms \n\t返回值: {}", uuid, endTime - startTime, GsonUtil.toJson(result));
		} catch (Throwable e) {
			log.error("\n\t请求标识: {} \n\t返回异常: {}", uuid, e.getMessage());
		}

		return result;
	}

}
