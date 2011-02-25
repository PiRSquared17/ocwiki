package org.ocwiki.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateUtils {

	private DateUtils() {
	}

	public static Date getYesterday() {
		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.add(Calendar.DATE, -1);
		return yesterday.getTime();
	}
	
	public static Date getLastWeek() {
		GregorianCalendar dayBeforeThisWeek = new GregorianCalendar();
		int dayFromMonday = (dayBeforeThisWeek.get(Calendar.DAY_OF_WEEK) + 7 - Calendar.MONDAY) % 7;
		dayBeforeThisWeek.add(Calendar.DATE, -dayFromMonday-1);
		return dayBeforeThisWeek.getTime();
	}
	
	public static Date getLastMonth() {
		GregorianCalendar dayBeforeThisMonth = new GregorianCalendar();
		dayBeforeThisMonth.set(Calendar.DATE, 1);
		dayBeforeThisMonth.add(Calendar.DATE, -1);
		return dayBeforeThisMonth.getTime();
	}
	
}
