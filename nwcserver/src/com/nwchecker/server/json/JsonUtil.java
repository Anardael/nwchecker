package com.nwchecker.server.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	public static <E extends Json> List<E> createJsonList(Class<E> clazz,
			List<?> sourceList) {
		List<E> jsonList = new ArrayList<E>();
		for (Object a : sourceList){
			jsonList.add(createJson(clazz, a));
		}
		return jsonList;
	}

	static <E extends Json> E createJson(Class<E> clazz, Object listElement) {
		try {
			Constructor<E> c = clazz.getConstructor(listElement.getClass());
			E instance = c.newInstance(listElement);
			return instance;
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}