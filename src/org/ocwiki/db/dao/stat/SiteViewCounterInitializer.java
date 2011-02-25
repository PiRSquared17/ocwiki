package org.ocwiki.db.dao.stat;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ocwiki.data.stat.DailyStatistic;

public class SiteViewCounterInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		DailyStatistic lastStatistic = DailyStatisticDAO.fetchLastStatistic();
		SiteViewCounter.counter = new AtomicLong(lastStatistic.getViewCount());
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		DailyStatisticDAO.saveCurrentStatistic();
	}

}
