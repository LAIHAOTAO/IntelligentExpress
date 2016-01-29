/**
 * Json格式包装工具类
 * @author lai.ht
 * @Date 2015.11.22
 * @Version: 1.0
 * @Description: 适用于两种情况下的json格式转换（键值对都是纯字符串，键是字符串值是数组）
 */

package com.ericlai.express.util;

import java.util.ArrayList;
import java.util.Map;

public class JsonBuildUtil {
	
	/**
	 * 对外暴露的接口
	 * 打包成对象
	 * @param mainkeyAndValue 第一层的键值对Map（可为null，表示只含数组的json格式字符串）
	 * @param subKeyList 数组对应的键（该键和上面的同级）List（可为null，表示只含普通字符的json格式字符串）
	 * @param subValueList 数组里（子层）的键和值List（可为null，表示只含普通字符的json格式字符串）
	 * @return 完成json格式的字符串
	 */
	public static String packToObject(Map<String, String> mainkeyAndValue, ArrayList<String> subKeyList, ArrayList<Map<String, String>> subValueList) {
		StringBuilder tmpJson = new StringBuilder();
		tmpJson.append("{");
		if (subKeyList != null && subValueList != null) {
			tmpJson.append(packToArray(subKeyList,subValueList));
			tmpJson.append(",");
		}
		if (mainkeyAndValue != null)
			tmpJson.append(packToKeyAndValue(mainkeyAndValue));
		else
			tmpJson.deleteCharAt(tmpJson.lastIndexOf(","));
		tmpJson.append("}");
		String json = tmpJson.toString();
		return json;
	}
	
	/**
	 * 打包成键值对
	 * @param param 键值对Map
	 * @return json格式的键值对字符串
	 */
	private static String packToKeyAndValue(Map<String, String> param) {
		StringBuilder tmpJson = new StringBuilder();
		if (param != null) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				tmpJson.append(pack(entry));
				tmpJson.append(",");
			}
		}
		tmpJson.deleteCharAt(tmpJson.lastIndexOf(","));
		String json = tmpJson.toString();
		return json;
	}
	
	/**
	 * 打包成键值对的辅助函数
	 * @param entry 遍历Map的工具
	 * @return json格式的键值对
	 */
	private static String pack(Map.Entry<String, String> entry) {
		StringBuilder tmpJson = new StringBuilder();
		tmpJson.append("\"");
		tmpJson.append(entry.getKey());
		tmpJson.append("\"");
		tmpJson.append(":");
		tmpJson.append("\"");
		tmpJson.append(entry.getValue());
		tmpJson.append("\"");
		String json = tmpJson.toString();
		return json;
	}
	
	/**
	 * 打包成含有数组的键值对
	 * @param keyList 数组的键
	 * @param valueList 数组的值
	 * @return json格式的数组字符串
	 */
	private static String packToArray(ArrayList<String> keyList,
			ArrayList<Map<String, String>> valueList) {
		StringBuilder tmpJson = new StringBuilder();
		int i = 0;
		for (String key : keyList) {
			tmpJson.append("\"");
			tmpJson.append(key);
			tmpJson.append("\"");
			tmpJson.append(":");
			if (valueList.get(i) != null) {
				tmpJson.append("[");
				for (Map.Entry<String, String> entry : valueList.get(i).entrySet()) {
					tmpJson.append("{");
					tmpJson.append(pack(entry));
					tmpJson.append("}");
					tmpJson.append(",");
				}
			}
			i++;
			tmpJson.deleteCharAt(tmpJson.lastIndexOf(","));
			tmpJson.append("]");
			tmpJson.append(",");
		}
		tmpJson.deleteCharAt(tmpJson.lastIndexOf(","));
		String json = tmpJson.toString();
		return json;
	}
	
}
