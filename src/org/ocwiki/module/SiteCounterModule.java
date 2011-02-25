package org.ocwiki.module;

import java.util.List;

import org.ocwiki.data.stat.DailyStatistic;
import org.ocwiki.db.dao.stat.DailyStatisticDAO;
import org.ocwiki.db.dao.stat.SiteViewCounter;
import org.ocwiki.util.DateUtils;

public class SiteCounterModule extends DefaultModule {

	private long todayViewCount;
	private long lastWeekViewCount;
	private long lastMonthViewCount;
	private long allTimeViewCount;

	@Override
	public void init() throws Exception {
		super.init();
		List<DailyStatistic> stats = DailyStatisticDAO.fetch(
				DateUtils.getLastMonth(), DateUtils.getLastWeek(),
				DateUtils.getYesterday());
		allTimeViewCount = SiteViewCounter.get();
		todayViewCount = allTimeViewCount
				- (stats.size() >= 1 ? stats.get(0).getViewCount() : 0);
		lastWeekViewCount = allTimeViewCount
				- (stats.size() >= 2 ? stats.get(1).getViewCount() : 0);
		lastMonthViewCount = allTimeViewCount
				- (stats.size() >= 3 ? stats.get(2).getViewCount() : 0);
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
