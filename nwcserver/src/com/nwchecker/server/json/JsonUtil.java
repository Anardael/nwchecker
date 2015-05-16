package com.nwchecker.server.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	public static <E extends Json> List<E> createJsonList(Class<E> clazz,
			List<?> o) {
		List<E> jsonList = new ArrayList<E>();
		for (Object a : o){
			jsonList.add(createJson(clazz, a));
		}
		return jsonList;
	}

	static <E extends Json> E createJson(Class<E> clazz, Object o) {
		try {
			Constructor<E> c = clazz.getConstructor(o.getClass());
			E instance = c.newInstance(o);
			return instance;
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}