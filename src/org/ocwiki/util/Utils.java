package org.ocwiki.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.ocwiki.data.Entity;

public final class Utils {

	private Utils() {
	}
	
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
	
	public static String join(Object... objects) {
		return StringUtils.join(objects);
	}
	
	public static String join(String seperator, Object... objects) {
		return StringUtils.join(objects, seperator);
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

	private static char[] SPECIAL_CHARACTERS = { ' ', '!', '"', '#', '$', '%',
			'*', '+', ',', ':', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
			'`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
			'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
			'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
			'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
			'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
			'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
			'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
			'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
			'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
			'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	private static char[] REPLACEMENTS = { '-', '\0', '\0', '\0', '\0', '\0',
			'\0', '_', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '_',
			'\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E',
			'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
			'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
			'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
			'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
			'U', 'u', };

	/**
	 * Convert a string (article name, user name,...) to URL-friendly version.
	 * That means:
	 * <ul>
	 * <li>No ASCII Control characters</li>
	 * <li>No Vietnamese accent</li>
	 * <li>Less than or equal to 236 in length: to be used as file name,
	 * recommended path length is <a
	 * href="http://msdn.microsoft.com/en-us/library/aa365247.aspx#maxpath">
	 * 260</a> in Windows, minus 4 of ".html" thing, minus 19 of maximum length
	 * of id, minus 1 of a dash.</li>
	 * <li>No trailing slashes.</li>
	 * <li>No URL- and file system- special characters: less than, greater than,
	 * double quote, pipe, asterisk, space, colon, comma, exclamation, question
	 * mark</li>
	 * </ul>
	 * 
	 * @param s
	 * @return
	 */
	public static String toUrlFriendly(String s) {
		if (s == null) {
			return null;
		}
		int maxLength = Math.min(s.length(), 236);
		char[] buffer = new char[maxLength];
		int n = 0;
		for (int i = 0; i < maxLength; i++) {
			char ch = s.charAt(i);
			int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
			if (index >= 0) {
				buffer[n] = REPLACEMENTS[index];
			} else {
				buffer[n] = ch;
			}
			// skip not printable characters
			if (buffer[n] > 31) { 
				n++;
			}
		}
		// skip trailing slashes
		while (n > 0 && buffer[n - 1] == '/') {
			n--;
		}
		return String.valueOf(buffer, 0, n);
	}
	
}
