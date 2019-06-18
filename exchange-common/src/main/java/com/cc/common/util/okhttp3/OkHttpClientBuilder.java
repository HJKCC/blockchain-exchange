package com.cc.common.util.okhttp3;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/14 16:38
 * @Description OkhttpBuilder
 */
public class OkHttpClientBuilder {
	private OkHttpClient.Builder builder = new OkHttpClient.Builder();

	/**
	 * 连接池
	 */
	private ConnectionPool connectionPool;

	/**
	 * 为新连接设置默认连接超时，单位毫秒
	 */
	private long connectTimeout = 10000;
	private int readTimeout = 10000;
	private int writeTimeout = 10000;

	/**
	 * 构建客户端
	 * @return okhttp client
	 */
	public OkHttpClient build(){
		if (connectionPool != null) {
			builder.connectionPool(connectionPool);
		}
		if (connectTimeout != 10000) {
			builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
		}
		if (readTimeout != 10000) {
			builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
		}
		if (writeTimeout != 10000) {
			builder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
		}
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostName, port));
		return builder.build();
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}

	public long getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}
}
