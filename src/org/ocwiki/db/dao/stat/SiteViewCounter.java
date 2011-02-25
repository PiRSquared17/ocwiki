package org.ocwiki.db.dao.stat;

import java.util.concurrent.atomic.AtomicLong;

public final class SiteViewCounter {

	static AtomicLong counter;
	
	public static long get() {
		return counter.get();
	}

	public static long incrementAndGet() {
		return counter.incrementAndGet();
	}

}
