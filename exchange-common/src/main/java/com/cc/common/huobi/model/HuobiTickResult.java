package com.cc.common.huobi.model;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/13 10:49
 * @Description HuobiTickResult
 */
public class HuobiTickResult<T> extends HuobiBaseResult {
	private String ch;
	private T tick;

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public T getTick() {
		return tick;
	}

	public void setTick(T tick) {
		this.tick = tick;
	}
}
