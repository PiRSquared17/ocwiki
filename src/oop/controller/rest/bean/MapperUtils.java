package oop.controller.rest.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public final class MapperUtils {

	private MapperUtils() {
	}
	
	/**
	 * Không thể dùng <code>Mapper&lt;U,V&gt; mapper</code>. 
	 * @param <U>
	 * @param <V>
	 * @param values
	 * @param mapper
	 * @return
	 */
	public static <U,V> List<U> applyAll(List<V> values, Mapper mapper) {
		List<U> beans = new ArrayList<U>();
		for (V value : values) {
			beans.add((U) mapper.apply(value));
		}
		return beans;
	}
	
	public static <U, V> void applyAll(Collection<U> beans,
			Collection<V> entities, Mapper<U, V> mapper) {
		for (V entity : entities) {
			beans.add(mapper.apply(entity));
		}
	}

	public static <U, V> void getAll(Collection<U> beans,
			Collection<V> entities, Mapper<U, V> mapper) {
		for (U bean : beans) {
			entities.add(mapper.get(bean));
		}
	}

}
