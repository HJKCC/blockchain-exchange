package com.cc.common.huobi.face;

@FunctionalInterface
public interface JsonParser<T> {

  T parseJson(String str);
}
