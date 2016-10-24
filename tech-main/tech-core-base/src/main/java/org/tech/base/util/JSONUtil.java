package org.tech.base.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JSONUtil {

	/**
	 * 封装将json对象转换为java集合对象
	 * 
	 * @param <T>
	 * @param clazz
	 * @param jsons
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getJavaCollection(Class<T> clazz, String jsons) {
		List<T> objs = null;
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsons);
		if (jsonArray != null) {
			objs = new ArrayList<T>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				T obj = (T) JSONObject.toBean(jo, clazz);
				objs.add(obj);
			}
		}
		return objs;
	}

}
