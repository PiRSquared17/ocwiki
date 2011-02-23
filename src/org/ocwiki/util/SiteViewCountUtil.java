package org.ocwiki.util;

import java.util.concurrent.atomic.AtomicLong;

import org.ocwiki.controller.OcwikiApp;

public final class SiteViewCountUtil {

	public static long get() {
		AtomicLong counter = (AtomicLong) OcwikiApp.get().getServletContext()
				.getAttribute("viewCount");
		return counter.get();
	}

	public static void initialize(long viewCount) {
		OcwikiApp.get().getServletContext()
				.setAttribute("viewCount", new AtomicLong(viewCount));
	}

	public static long incrementAndGet() {
		AtomicLong counter = (AtomicLong) OcwikiApp.get().getServletContext()
				.getAttribute("viewCount");
		return counter.incrementAndGet();
	}

}
