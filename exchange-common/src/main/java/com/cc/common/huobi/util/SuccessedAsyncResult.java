package com.cc.common.huobi.util;

import com.cc.common.huobi.exception.HuobiApiException;
import com.cc.common.huobi.face.AsyncResult;

public class SuccessedAsyncResult<T> implements AsyncResult<T> {

  private final T data;

  public SuccessedAsyncResult(T data) {
    this.data = data;
  }

  @Override
  public HuobiApiException getException() {
    return null;
  }

  @Override
  public boolean succeeded() {
    return true;
  }

  @Override
  public T getData() {
    return data;
  }
}
