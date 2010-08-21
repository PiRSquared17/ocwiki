package oop.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

import oop.data.Entity;

public class Utils {

	public static int min(int... values) {
		int min = Integer.MAX_VALUE;
		for (int v : values) {
			if (v < min) {
				min = v;
			}
		}
		return min;
	}

	public static int sum(int... values) {
		int sum = 0;
		for (int i : values) {
			sum += i;
		}
		return sum;
	}

	public static void deepCopy(int[][][] source, int[][][] dest) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[i].length; j++) {
				for (int k = 0; k < source[i][j].length; k++) {
					dest[i][j][k] = source[i][j][k];
				}
			}
		}
	}
	
	public static String urlEncode(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static boolean isNull(Object... objects) {
		for (Object object : objects) {
			if (object != null) {
				return false;
			}
		}
		return true;
	}

	public static int hashCode(long value) {
		return (int)(value ^ (value >>> 32));
	}

	public static boolean isEmpty(Collection<?> coll) {
		return coll == null || coll.isEmpty();
	}
	
	public static <T extends Entity> T findById(Collection<T> coll, long id) {
		for (T t : coll) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	public static <T extends Copiable<T>> T replaceByCopy(List<T> list,
			int index) {
		T newCopy = list.get(index).copy();
		list.set(index, newCopy);
		return newCopy;
	}
	
}
