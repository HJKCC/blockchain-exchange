package com.cc.common.huobi.util;

import com.cc.common.huobi.exception.HuobiApiException;
import com.cc.common.huobi.face.AsyncResult;

public class FailedAsyncResult<T> implements AsyncResult<T> {

  private final HuobiApiException exception;

  public FailedAsyncResult(HuobiApiException exception) {
    this.exception = exception;
  }

  @Override
  public HuobiApiException getException() {
    return exception;
  }

  @Override
  public boolean succeeded() {
    return false;
  }

  @Override
  public T getData() {
    return null;
  }
}
