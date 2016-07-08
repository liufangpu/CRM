package com.atguigu.crm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MyParseUtils {

private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 真正需要转换的有两处:
	 * 1. 若是 LIKE 的比较方式, 则需要把 val 的前后加上 %
	 * 2. 若目标类型是 Date 类型, 则需要把 val 转为 Date 类型
	 * @param requestParams
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> parseRequestParams2MyBatisParams(
			Map<String, Object> requestParams) throws ParseException {
		Map<String, Object> result = new HashMap<>();
		
		for(Map.Entry<String, Object> entry: requestParams.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if(val == null || val.toString().trim().equals("")){
				continue;
			}
			
			if(key.startsWith("D_")){
				key = key.substring(2);
				val = dateFormat.parse((String)val);
			}
			if(key.startsWith("LIKE_")){
				key = key.substring(5);
				val = "%" + val + "%";
			}
			if(key.startsWith("EQ_")){
				key = key.substring(3);
			}
			
			
			
			result.put(key, val);
		}
		
		return result;
	}
	
	public static String parseRequestParams2QueryString(Map<String, Object> params) {
		StringBuilder result = new StringBuilder("");
		
		for(Map.Entry<String, Object> entry: params.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if(val == null || val.toString().trim().equals("")){
				continue;
			}
			
			result.append("search_")
			      .append(key)
			      .append("=")
			      .append(val)
			      .append("&");
		}
		
		if(result.length() > 1){
			result = result.replace(result.length() - 1, result.length(), "");
		}
		return result.toString();
	}
}
