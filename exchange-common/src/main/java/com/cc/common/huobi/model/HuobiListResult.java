package com.cc.common.huobi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/13 10:49
 * @Description HuobiListResult
 */
public class HuobiListResult<T> extends HuobiBaseResult {
	private String ch;
	private List<T> data = new ArrayList<>();

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
