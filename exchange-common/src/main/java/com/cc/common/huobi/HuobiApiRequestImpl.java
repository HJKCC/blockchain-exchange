package com.cc.common.huobi;

import com.cc.common.huobi.exception.HuobiApiException;
import com.cc.common.huobi.face.AsyncResult;
import com.cc.common.huobi.face.ResponseCallback;
import com.cc.common.huobi.model.RequestAndJsonParser;
import com.cc.common.huobi.util.FailedAsyncResult;
import com.cc.common.huobi.util.HuobiAuthentication;
import com.cc.common.huobi.util.SuccessedAsyncResult;
import com.cc.common.huobi.util.UrlParamsBuilder;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class HuobiApiRequestImpl {
  private static final Logger logger = LoggerFactory.getLogger(HuobiApiRequestImpl.class);

  private OkHttpClient okHttpClient;

  /**
   * apikey 可向火币客服申请。在“账户 - API管理”页面进行相关操作。
   * 其中AccessKey为API 访问密钥，SecretKey为用户对请求进行签名的密钥（仅申请时可见）
   */
  private String accessKey;
  /**
   * apikey 可向火币客服申请。在“账户 - API管理”页面进行相关操作。
   * 其中AccessKey为API 访问密钥，SecretKey为用户对请求进行签名的密钥（仅申请时可见）
   */
  private String secretKey;
  /**
   * 请求地址  协议+域名
   */
  private String baseUrl;
  /**
   * 签名地址  域名
   */
  private String domainName;

  public HuobiApiRequestImpl() {
    this.baseUrl = "https://api.hbdm.com";
  }

  public HuobiApiRequestImpl(String accessKey, String secretKey, String baseUrl, OkHttpClient okHttpClient) {
    try {
      this.domainName = new URL(this.baseUrl).getHost();
      this.accessKey = accessKey;
      this.secretKey = secretKey;
      this.baseUrl = baseUrl;
		this.okHttpClient = okHttpClient;
    } catch (Exception e) {
      throw new HuobiApiException(
              HuobiApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
    }
  }

  public Request createRequestByGet(String methodPath, UrlParamsBuilder builder) {
    return createRequest(baseUrl, methodPath, builder);
  }

  public Request createRequestByPost(String methodPath, UrlParamsBuilder builder) {
    return createRequest(baseUrl, methodPath, builder.setPostMode(true));
  }

  public Request createRequest(String baseUrl, String methodPath, UrlParamsBuilder builder) {
    String requestUrl = baseUrl + methodPath;
    // 默认为 get 请求
    if (builder != null) {
      if (builder.hasPostParam()) {
        return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
            .addHeader("Content-Type", "application/json").build();
      } else {
        return new Request.Builder().url(requestUrl + builder.buildUrl())
            .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
      }
    } else {
      return new Request.Builder().url(requestUrl)
          .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
    }
  }

  public Request createRequestByPostWithSignature(String methodPath, UrlParamsBuilder builder) {
    return createRequestWithSignature(baseUrl, methodPath, domainName, builder.setPostMode(true));
  }

  public Request createRequestByGetWithSignature(String methodPath, UrlParamsBuilder builder) {
    return createRequestWithSignature(baseUrl, methodPath, domainName, builder);
  }

  public Request createRequestWithSignature(String baseUrl, String methodPath, String domainName, UrlParamsBuilder builder) {
    if (builder == null) {
      throw new HuobiApiException(HuobiApiException.RUNTIME_ERROR,
              "[Invoking] Builder is null when create request with Signature");
    }
    String requestUrl = baseUrl + methodPath;
    if (builder.hasPostParam()) {
      new HuobiAuthentication().createSignature(accessKey, secretKey, "POST", domainName, methodPath, builder);
      requestUrl += builder.buildUrl();
      return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
              .addHeader("Content-Type", "application/json").build();
    } else {
      new HuobiAuthentication().createSignature(accessKey, secretKey, "GET", domainName, methodPath, builder);
      requestUrl += builder.buildUrl();
      return new Request.Builder().url(requestUrl)
              .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
    }
  }

  public <T> T callSync(RequestAndJsonParser<T> requestAndJsonParser) {
    try {
      String str = "";
      Response response = okHttpClient.newCall(requestAndJsonParser.request).execute();

      // 判断响应状态
      int responseCode = response.code();
      if (responseCode >= 300 || responseCode < 200) {
        logger.error(requestAndJsonParser.request.toString() + "\n"  +
                "Response code is " + responseCode + "\n"  +
                "Response message is " + response.message());
        return null;
      } else {
        str = response.body().string();
        response.close();
      }

      return requestAndJsonParser.jsonParser.parseJson(str);
    } catch (HuobiApiException e) {
      throw e;
    } catch (Exception e) {
      throw new HuobiApiException(
              HuobiApiException.ENV_ERROR, "[Invoking] Unexpected error: " + e.getMessage());
    }
  }

  public <T> void callASync(RequestAndJsonParser<T> requestAndJsonParser, ResponseCallback<AsyncResult<T>> callback) {
    try {
      Call call = okHttpClient.newCall(requestAndJsonParser.request);
      call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          FailedAsyncResult<T> result = new FailedAsyncResult<>(
                  new HuobiApiException(HuobiApiException.RUNTIME_ERROR,
                          "[Invoking] Rest api call failed"));
          try {
            callback.onResponse(result);
          } catch (Exception exception) {
            logger.error("[Invoking] Unexpected error: " + exception.getMessage(), e);
          }
        }

        @Override
        public void onResponse(Call call, Response response) {
          String str = "";
          try {
            if (response != null && response.body() != null) {
              str = response.body().string();
              response.close();
            }

          } catch (HuobiApiException e) {
            FailedAsyncResult<T> result = new FailedAsyncResult<>(e);
            callback.onResponse(result);
            return;
          } catch (Exception e) {
            FailedAsyncResult<T> result = new FailedAsyncResult<>(
                    new HuobiApiException(
                            HuobiApiException.RUNTIME_ERROR, "[Invoking] Rest api call failed"));
            callback.onResponse(result);
            return;
          }
          try {
            SuccessedAsyncResult<T> result = new SuccessedAsyncResult<>(
                    requestAndJsonParser.jsonParser.parseJson(str));
            callback.onResponse(result);
          } catch (Exception e) {
            logger.error("[Invoking] Unexpected error: " + e.getMessage(), e);
          }

        }
      });
    } catch (Throwable e) {
      throw new HuobiApiException(
              HuobiApiException.ENV_ERROR, "[Invoking] Unexpected error: " + e.getMessage());
    }
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    try {
      this.domainName = new URL(baseUrl).getHost();
      this.baseUrl = baseUrl;
    } catch (Exception e)
    {
      throw new HuobiApiException(
              HuobiApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
    }
  }

	public OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}

	public void setOkHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
	}
}
