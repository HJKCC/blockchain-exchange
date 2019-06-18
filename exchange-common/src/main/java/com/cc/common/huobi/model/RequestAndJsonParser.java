package com.cc.common.huobi.model;

import com.cc.common.huobi.face.JsonParser;
import okhttp3.Request;

public class RequestAndJsonParser<T> {

  public Request request;
  public JsonParser<T> jsonParser;
}
