package oop.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

public final class MapperUtils {

	private MapperUtils() {
	}
	
	public static <U,V> List<U> applyAll(List<V> values, Mapper<U, V> mapper) {
		List<U> beans = new ArrayList<U>();
		for (V value : values) {
			beans.add(mapper.apply(value));
		}
		return beans;
	}
	
}
