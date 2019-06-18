package com.cc.common.huobi.face;

@FunctionalInterface
public interface Handler<T> {

  void handle(T t);
}
