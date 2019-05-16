package com.cc.common.model;

import com.cc.common.constant.ExchangeCST;

import java.util.ArrayList;
import java.util.List;

public class ResInfoVO {
	private String code;
	private String info;
	private Object object;
	private List list = new ArrayList<>();
	private Integer size;

	public String getResultCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
		this.size = list.size();
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

	public ResInfoVO() {
		super();
		this.code = ExchangeCST.SUCCESS;
		this.info = ExchangeCST.SUCCESS_INFO;
	}

	public ResInfoVO(String code) {
		super();
		this.code = code;
		this.info = ExchangeCST.SUCCESS_INFO;
	}

	public ResInfoVO(String code, String info) {
		super();
		this.code = code;
		this.info = info;
	}
}
