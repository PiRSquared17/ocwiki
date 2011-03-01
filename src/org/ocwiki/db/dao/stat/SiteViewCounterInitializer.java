package org.ocwiki.db.dao.stat;

import java.util.EventObject;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import org.ocwiki.controller.OcwikiAppListener;
import org.ocwiki.data.stat.DailyStatistic;

public class SiteViewCounterInitializer implements OcwikiAppListener {

	private Timer timer = new Timer(true);
	
	@Override
	public void appInitialized(EventObject evt) {
		DailyStatistic lastStatistic = DailyStatisticDAO.fetchLastStatistic();
		SiteViewCounter.counter = new AtomicLong(lastStatistic.getViewCount());
		// automatically save current statistics every half-day
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				saveCurrentStatistics();
			}
		}, 0, 1000*60*60*12);
	}

	@Override
	public void appDestroying(EventObject evt) {
		saveCurrentStatistics();
	}

	private void saveCurrentStatistics() {
		DailyStatisticDAO.saveCurrentStatistic();
	}

}
