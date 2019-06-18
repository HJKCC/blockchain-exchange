package com.cc.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.cc.common.huobi.model.HuobiListResult;
import com.cc.common.huobi.model.KLineVO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/13 14:32
 * @Description FastJsonUtil
 */
public class FastJsonUtil {
	/**
	 * json字符串转map集合
	 * @param jsonStr
	 * @return
	 */
	public static HashMap<String, String> json2Map(String jsonStr){
		return JSON.parseObject(jsonStr, new HashMap<String, String>().getClass());
	}

	/**
	 * json字符串转换成对象
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> cls){

		return JSON.parseObject(jsonString, cls);
	}

	/**
	 * json字符串转换成对象
	 * @param jsonString
	 * @param typeReference
	 * @return
	 */
	public static <T> T fromJson(String jsonString, TypeReference<T> typeReference){

		return JSON.parseObject(jsonString, typeReference);
	}

	/**
	 * json字符串转换成ArrayList集合
	 * (需要实体类)
	 * @param jsonString
	 * @return
	 */
	public static <T> ArrayList<T> fromJsonList(String jsonString, Class cls){
		ArrayList<T> list = null;
		try {
			list = (ArrayList<T>) JSON.parseArray(jsonString, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * json 转 JSONArray
	 * (不需要实体类)
	 * @param jsonStr
	 * @return
	 */
	public static JSONArray toJSONArray(String jsonStr){
		return JSON.parseArray(jsonStr);
	}

	/**
	 * 对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return JSON.toJSONString(obj);
	}
}