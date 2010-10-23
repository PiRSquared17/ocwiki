package oop.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import oop.data.Entity;

public class Utils {

	public static boolean isNumberic(Object value) {
		if(value==null){
			return false;
		}
		try {			
			Long.parseLong(value.toString());			
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

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
			throw new RuntimeException("never!!!", e);
		}
	}

	public static String urlDecode(String url) {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("never!!!", e);
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
		return (int) (value ^ (value >>> 32));
	}

	public static boolean isEmpty(Collection<?> coll) {
		return coll == null || coll.isEmpty();
	}

	public static boolean isNotEmpty(Collection<?> coll) {
		return coll != null && coll.size() > 0;
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

	private static final char[] HEXES = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getHex( byte [] raw ) {
        if (raw == null) {
            return null;
        }
        final char[] hex = new char[2 * raw.length];
        for (int i = 0, j = 0; i < raw.length; i++) {
            byte b = raw[i];
            hex[j++] = HEXES[(b & 0xF0) >> 4];
            hex[j++] = HEXES[(b & 0x0F)];
        }
        return new String(hex);
    }

	public static String md5(String x) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] digest = md5.digest(x.getBytes());
			String hex = getHex(digest);
			return hex;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("never!", e);
		}
	}
	
	public static int indexOf(List<? extends Entity> entities, long id) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public static <T extends Entity> T findById(List<T> entities, long id) {
		for (T t : entities) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}
	
}
