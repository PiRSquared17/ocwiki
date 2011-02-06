package oop.taglib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;

public final class UtilFunctions {
	
	private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public static long floor(double a) {
		return (long)Math.floor(a);
	}

	public static long ceil(double a) {
		return (long)Math.ceil(a);
	}
	
	public static long round(double a) {
		return (long)Math.round(a);
	}
	
	public static int size(Object o) {
		if (o instanceof Collection) {
			return ((Collection<?>)o).size();
		}
		if (o instanceof Map) {
			return ((Map<?,?>)o).size();
		}
		return ArrayUtils.getLength(o);
	}
	
	public static String ellipsize(String s, int length) {
		s = StringEscapeUtils.unescapeHtml(s);
		if (s.length() > length) {
			s = s.substring(0, length-3) + "...";
		}
		s = StringEscapeUtils.escapeHtml(s);
		return s;
	}
	
	public static String toAlpha(int order) {
		char ch = (char) (order + 'A');
		if (ch > 'Z' || ch < 'A') {
			throw new IllegalArgumentException("Order is out of range");
		}
		return Character.toString(ch);
	}
	
	public static Date parseDate(String str) throws ParseException {
		return new SimpleDateFormat(DATE_FORMAT).parse(str);
	}
	
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	
	public static String formatDateTime(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
	}
	
	public static String stripHTML(String html) {
		return html.replaceAll("\\<.*?\\>", "");
	}

	/**
	 * Kiểm tra a có phải là b hoặc lớp tổ tiên của b hay không.
	 * Nếu một tham số không phải kiểu Class, nó sẽ được coi là
	 * chứa tên của lớp. 
	 * @param a đối tượng kiểu Class hoặc String
	 * @param b đối tượng kiểu Class hoặc String
	 * @return true nếu a là b hoặc tổ tiên của b, false nếu ngược lại
	 */
	public static boolean isAssignableFrom(Object a, Object b) {
		if (a == null || b == null) {
			return false;
		}
		try {
			Class<?> classA;
			if (a instanceof Class<?>) {
				classA = (Class<?>)a;
			} else {
				classA = Class.forName(a.toString());
			}
			
			Class<?> classB;
			if (b instanceof Class<?>) {
				classB = (Class<?>)b;
			} else {
				classB = Class.forName(b.toString());
			}

			return classA.isAssignableFrom(classB);
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static String name(Enum<?> e) {
		return e.name();
	}

	/**
	 * Kiểm tra <code>coll</code> có chứa phần tử obj hay không.
	 * <code>coll</code> có thể là Collection, Map hoặc mảng. Nếu
	 * <code>coll</code> là Map, kiểm tra trong tập các giá trị của 
	 * nó (không kiểm tra khoá). 
	 * @param coll Collection, Map hoặc array của các đối tượng
	 * @param obj đối tượng cần tìm
	 * @return 
	 */
	public static boolean contains(Object coll, Object obj) {
		if (coll instanceof Collection<?>) {
			return ((Collection<?>)coll).contains(obj);
		}
		if (coll instanceof Map<?,?>) {
			return ((Map<?,?>)coll).containsValue(obj);
		}
		if (coll instanceof Object[]) {
			return ArrayUtils.contains((Object[])coll, obj);
		}
		return false;
	}
	
}
