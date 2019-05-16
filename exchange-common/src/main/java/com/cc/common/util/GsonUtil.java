package com.cc.common.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
* @Description GsonUtil.java
* @author cc_2471082434@qq.com
* @date 2016年8月1日 下午2:18:06
 */
public class GsonUtil {
    public static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static <T> T fromJson(String src, java.lang.reflect.Type type) {
        return GSON.fromJson(src, type);
    }
    
    public static <T> List<T> fromJsonList(String src, java.lang.reflect.Type type) {
    	JsonParser parser = new JsonParser();		
		JsonArray jsonArray = parser.parse(src).getAsJsonArray();
		
		ArrayList<T> arrayList = new ArrayList<T>();
		for(JsonElement jsonElement : jsonArray ){   
			T t = GSON.fromJson(jsonElement, type);   
			arrayList.add(t);   
		}
		
		return arrayList;
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }
}
