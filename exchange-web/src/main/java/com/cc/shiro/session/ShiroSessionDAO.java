package com.cc.shiro.session;

import com.cc.common.util.SerializeUtils;
import com.cc.common.util.redis.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 针对自定义的ShiroSession的Redis CRUD操作，通过isChanged标识符，确定是否需要调用Update方法
 * 通过配置securityManager在属性cacheManager查找从缓存中查找Session是否存在，如果找不到才调用下面方法
 * Shiro内部相应的组件（DefaultSecurityManager）会自动检测相应的对象（如Realm）
 * 是否实现了CacheManagerAware并自动注入相应的CacheManager。
 */
public class ShiroSessionDAO extends CachingSessionDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ShiroSessionDAO.class);

	// 保存到Redis中key的前缀 prefix+sessionId
	private String prefix = "";

	// 设置会话的过期时间
	private int seconds = 0;

	// 特殊配置 只用于没有Redis时 将Session放到EhCache中
	private Boolean onlyEhCache;

	@Autowired
	private RedisClient redisClient;

	/**
	 * 重写CachingSessionDAO中readSession方法，
	 * 如果Session中没有登陆信息就调用doReadSession方法从Redis中重读
	 */
	@Override
	public Session readSession(Serializable sessionId)
			throws UnknownSessionException {
		Session cached = null;
		try {
			cached = super.getCachedSession(sessionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (onlyEhCache) {
			return cached;
		}
		// 如果缓存不存在或者缓存中没有登陆认证后记录的信息就重新从Redis中读取
		if (cached == null || cached.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
			try {
				cached = this.doReadSession(sessionId);
				if (cached == null) {
					throw new UnknownSessionException();
				} else {
					// 重置Redis中缓存过期时间并缓存起来 只有设置change才能更改最后一次访问时间
					((ShiroSession) cached).setChanged(true);
					super.update(cached);
				}
			} catch (Exception e) {
				logger.warn("There is no session with id [" + sessionId + "]");
			}
		}
		return cached;
	}

	/**
	 * 从Redis中读取Session,并重置过期时间
	 * 
	 * @param sessionId
	 *            会话ID
	 * @return ShiroSession
	 */
	@Override
	public Session doReadSession(Serializable sessionId) {
		Session session = null;
		Jedis jedis = null;
		try {
			jedis = redisClient.getResource();
			String key = prefix + sessionId;
			String value = jedis.get(key);
			if (StringUtils.isNotBlank(value)) {
				session = SerializeUtils.deserializeFromString(value);
//				logger.info("shiro session id {} 被读取", sessionId);
			}
		} catch (Exception e) {
			logger.warn("读取Session失败", e);
		} finally {
			redisClient.returnResource(jedis);
		}

		return session;
	}

	/**
	 * 如DefaultSessionManager在创建完session后会调用该方法；
	 * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
	 * 返回会话ID；主要此处返回的ID.equals(session.getId())；
	 */
	@Override
	public Serializable doCreate(Session session) {
		// 创建一个Id并设置给Session
		Serializable sessionId = this.generateSessionId(session);
		assignSessionId(session, sessionId);
		if (onlyEhCache) {
			return sessionId;
		}
		Jedis jedis = null;
		try {
			jedis = redisClient.getResource();
			// session由Redis缓存失效决定，这里只是简单标识
			session.setTimeout(seconds);
			jedis.setex(prefix + sessionId, seconds,
					SerializeUtils.serializeToString((ShiroSession) session));
			logger.info("shiro session id {} 被创建", sessionId);
		} catch (Exception e) {
			logger.warn("创建Session失败", e);
		} finally {
			redisClient.returnResource(jedis);
		}
		return sessionId;
	}

	/**
	 * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
	 */
	@Override
	public void doUpdate(Session session) {
		// 如果会话过期/停止 没必要再更新了
		try {
			if (session instanceof ValidatingSession
					&& !((ValidatingSession) session).isValid()) {
				return;
			}
		} catch (Exception e) {
			logger.error("ValidatingSession error");
		}

		if (onlyEhCache) {
			return;
		}

		Jedis jedis = null;
		try {
			if (session instanceof ShiroSession) {
				// 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
				ShiroSession ss = (ShiroSession) session;
				if (!ss.isChanged()) {
					return;
				}
				Transaction tx = null;
				try {
					jedis = redisClient.getResource();
					// 开启事务
					tx = jedis.multi();
					ss.setChanged(false);
					ss.setLastAccessTime(new Date());
					tx.setex(prefix + session.getId(), seconds,
							SerializeUtils.serializeToString(ss));
//					logger.info("shiro session id {} 被更新", session.getId(), session.getClass().getName());

					// 执行事务
					tx.exec();
				} catch (Exception e) {
					if (tx != null) {
						// 取消执行事务
						tx.discard();
					}
					throw e;
				}

			} else if (session instanceof Serializable) {
				jedis = redisClient.getResource();
				jedis.setex(prefix + session.getId(), seconds, SerializeUtils
						.serializeToString((Serializable) session));
				logger.info("ID {} classname {} 作为非ShiroSession对象被更新, ", session.getId(), session.getClass().getName());
			} else {
				logger.info("ID {} classname {} 不能被序列化 更新失败", session.getId(), session.getClass().getName());
			}
		} catch (Exception e) {
			logger.warn("更新Session失败", e);
		} finally {
			redisClient.returnResource(jedis);
		}
	}

	/**
	 * 删除会话；当会话过期/会话停止（如用户退出时）会调用
	 */
	@Override
	public void doDelete(Session session) {
		Jedis jedis = null;
		try {
			jedis = redisClient.getResource();
			jedis.del(prefix + session.getId());
			logger.info("shiro session id {} 被删除", session.getId());
		} catch (Exception e) {
			logger.warn("删除Session失败", e);
		} finally {
			redisClient.returnResource(jedis);
		}
	}

	/**
	 * 删除cache中缓存的Session
	 */
	public void uncache(Serializable sessionId) {
		try {
			Session session = super.getCachedSession(sessionId);
			super.uncache(session);
			logger.info("shiro session id {} 的缓存失效", sessionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前所有活跃用户，如果用户量多此方法影响性能
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		Jedis jedis = null;
		try {
			jedis = redisClient.getResource();
			Set<String> keys = jedis.keys(prefix + "*");
			if (CollectionUtils.isEmpty(keys)) {
				return null;
			}
			List<String> valueList = jedis.mget(keys.toArray(new String[0]));
			return SerializeUtils.deserializeFromStringController(valueList);
		} catch (Exception e) {
			logger.warn("统计Session信息失败", e);
		} finally {
			redisClient.returnResource(jedis);
		}
		return null;
	}

	/**
	 * 返回本机Ehcache中Session
	 */
	public Collection<Session> getEhCacheActiveSessions() {
		return super.getActiveSessions();
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setOnlyEhCache(Boolean onlyEhCache) {
		this.onlyEhCache = onlyEhCache;
	}
}
