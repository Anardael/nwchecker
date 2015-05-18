package com.nwchecker.server.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * <h1>Json Util</h1> 
 * Utility class created to transform entities and lists of entities into related Json objetcs
 * <p>
 * 
 * @author Boris Andreev
 * @version 1.0
 */
public class JsonUtil {
	
	/**
	 * Method to transform list of objects into a list of Json objetcs.
	 * Json class requires to have a constructor with argument type of source object.
	 * @param jsonClazz
	 * @param sourceList
	 * @return
	 */
	
	public static <E extends Json> List<E> createJsonList(Class<E> jsonClazz,
			List<?> sourceList) {
		List<E> jsonList = new ArrayList<E>();
		for (Object a : sourceList){
			jsonList.add(createJson(jsonClazz, a));
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