package oop.taglib;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import oop.data.Answer;
import oop.data.Question;
import oop.data.Section;
import oop.data.Test;

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
	
	@SuppressWarnings("unchecked")
	public static int size(Object o) {
		if (o instanceof Collection) {
			return ((Collection)o).size();
		}
		if (o instanceof Map) {
			return ((Map)o).size();
		}
		return 0;
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
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	
	public static String formatDateTime(Date date) {
		return new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
	}
	
	public static String stripHTML(String html) {
		return html.replaceAll("\\<.*?\\>", "");
	}

	public static double getMark(Test test, Map<Long, long[]> choices)
			throws SQLException {
		double total = 0.0;
		int count = 0;
		for (Section section : test.getSections()) {
			for (Question question : section.getQuestions()) {
				long[] c = choices.get(question.getId());
				if (c != null) {
					boolean correct = true;
					Arrays.sort(c);
					for (Answer answer : question.getAnswers()) {
						boolean choosed = Arrays
								.binarySearch(c, answer.getId()) >= 0;
						if (answer.isCorrect() != choosed) {
							correct = false;
							break;
						}
					}
					if (correct) {
						total += question.getMark();
					}
				}
				count++;
			}
		}
		return total / count;
	}
	
	public static boolean isAssignableFrom(String a, String b) {
		try {
			Class<?> classA = Class.forName(a);
			Class<?> classB = Class.forName(b);
			return classA.isAssignableFrom(classB);
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

}
