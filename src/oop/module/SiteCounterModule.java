package oop.module;

import java.util.Calendar;
import java.util.GregorianCalendar;

import oop.data.stat.DailyStatistic;
import oop.db.dao.stat.DailyStatisticDAO;
import oop.util.SiteViewCountUtil;

public class SiteCounterModule extends DefaultModule {

	private long todayViewCount;
	private long lastWeekViewCount;
	private long lastMonthViewCount;
	private long allTimeViewCount;
	
	@Override
	public void init() throws Exception {
		super.init();

		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.add(Calendar.DATE, -1);
		DailyStatistic yesterdayStatistic = DailyStatisticDAO.fetch(yesterday.getTime());

		GregorianCalendar dayBeforeThisWeek = new GregorianCalendar();
		int dayFromMonday = (dayBeforeThisWeek.get(Calendar.DAY_OF_WEEK) + 7 - Calendar.MONDAY) % 7;
		dayBeforeThisWeek.add(Calendar.DATE, -dayFromMonday-1);
		DailyStatistic dayBeforeThisWeekStatistic = DailyStatisticDAO.fetch(dayBeforeThisWeek.getTime());
		
		GregorianCalendar dayBeforeThisMonth = new GregorianCalendar();
		dayBeforeThisMonth.set(Calendar.DATE, 1);
		dayBeforeThisMonth.add(Calendar.DATE, -1);
		DailyStatistic dayBeforeThisMonthStatistic = DailyStatisticDAO.fetch(dayBeforeThisMonth.getTime());
		
		allTimeViewCount = SiteViewCountUtil.get();
		todayViewCount = allTimeViewCount - yesterdayStatistic.getViewCount();
		lastWeekViewCount = allTimeViewCount - dayBeforeThisWeekStatistic.getViewCount();
		lastMonthViewCount = allTimeViewCount - dayBeforeThisMonthStatistic.getViewCount();
	}

	public long getTodayViewCount() {
		return todayViewCount;
	}

	public long getLastWeekViewCount() {
		return lastWeekViewCount;
	}

	public long getLastMonthViewCount() {
		return lastMonthViewCount;
	}

	public long getAllTimeViewCount() {
		return allTimeViewCount;
	}
	
}
