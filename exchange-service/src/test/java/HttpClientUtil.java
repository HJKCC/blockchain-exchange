import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.*;

/**
 * @author Yang
 */
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static int TIMEOUT = 10000;//10s
	
	public static String httpGetStr(String url) throws IOException {
		return httpGetStr(url, TIMEOUT);
	}

	public static String httpGetStr(String url, int timeOut) throws IOException {
		return httpGetStr(null, null, url, timeOut);
	}

	public static String httpGetStr(Map<String, String> headers, String paramStr, String url) throws IOException {
		return httpGetStr(headers, paramStr, url, TIMEOUT);
	}

	public static String httpGetStr(Map<String, String> headers, String paramStr, String url, int timeOut) throws IOException {
		// 请求标识
		String uuid = UUID.randomUUID().toString();
		logger.info("\n\tGET请求标识: {}\n\t请求路径: {}\n\t请求参数: {}", uuid, url, paramStr);
		long startTime = System.currentTimeMillis();

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(timeOut) //设置连接超时时间
				.setConnectionRequestTimeout(timeOut) //设置请求超时时间
				.setSocketTimeout(timeOut) //设置socket超时时间
				.setRedirectsEnabled(true) //默认允许自动重定向
				.build();
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpClient client = new SSLClient();

		if (CommonUtils.isNotEmpty(paramStr)) {
			url += "?" + paramStr;
		}
		HttpGet getMethod = new HttpGet(url);
		getMethod.setConfig(requestConfig);

		if (CommonUtils.isNotEmpty(headers)) {
			Iterator<String> iterator = headers.keySet().iterator();
			while (iterator.hasNext()) {
				String headerKey = iterator.next();
				getMethod.addHeader(headerKey, headers.get(headerKey));
			}
		}

		HttpResponse response = client.execute(getMethod);
		long endTime = System.currentTimeMillis();

		String result = "";
		int httpStatusCode = response.getStatusLine().getStatusCode();
		if (200 == httpStatusCode) {
			// 得到执行结果
			result = EntityUtils.toString(response.getEntity());
			logger.info("\n\tGET请求标识: {} \n\t执行时间: {} ms \n\t返回值: {}", uuid, endTime - startTime, result);
		} else {
			result = "{\"status\":" + httpStatusCode + "}";
			logger.info("\n\tGET请求标识: {} \n\t执行时间: {} ms \n\t返回错误码: {}", uuid, endTime - startTime, httpStatusCode);
		}
		return result;
	}
}
