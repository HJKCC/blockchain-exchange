package com.cc.common.controller;

import com.cc.common.constant.ExchangeCST;
import com.cc.common.util.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* @Description BaseController.java 
* @author cc_2471082434@qq.com  
* @date 2016年8月5日 下午2:03:02
 */
public class BaseController {

    protected Map<String, Object> resultMap = null;
    protected List list = new ArrayList<>();
    protected int total = 0;
    protected Object object;
    
    public void initResultMap(){
    	resultMap = new HashMap<String, Object>();
    	resultMap.put(ExchangeCST.RESULT_CODE, ExchangeCST.SUCCESS);
    	resultMap.put(ExchangeCST.RESULT_INFO, ExchangeCST.SUCCESS_INFO);
    }
    
    public void initResultMap(HttpServletRequest request){
    	initResultMap();
    	
    	HttpSession session = (HttpSession) request.getSession();
    }

    public String jsonList() {
        this.resultMap.put("total", total);
        this.resultMap.put("rows", list);

        return GsonUtil.toJson(resultMap);
    }
    public String jsonList(List list) {
        this.resultMap.put("total", list.size());
        this.resultMap.put("rows", list);

        return GsonUtil.toJson(resultMap);
    }

    public String jsonObject() {
        this.resultMap.put("object", object);

        return GsonUtil.toJson(resultMap);
    }  
    
    public String jsonObject(Object object) {
    	this.resultMap.put("object", object);
    	
    	return GsonUtil.toJson(resultMap);
    }  

    public String jsonRes() {
        return GsonUtil.toJson(resultMap);
    }

    public String jsonRes(String code, String info) {
    	this.resultMap.put(ExchangeCST.RESULT_CODE, code);
        this.resultMap.put(ExchangeCST.RESULT_INFO, info);
        
        return jsonRes();
    }

}
