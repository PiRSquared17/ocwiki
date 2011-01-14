package oop.data.stat;

import java.util.Date;

public class DailyStatistic {

	private Date date;
	private long viewCount;
	private long editCount;
	private long resourceCount;
	private long memberCount;

	public DailyStatistic() {
	}

	public DailyStatistic(Date date, long viewCount, long editCount,
			long resourceCount, long memberCount) {
		super();
		this.date = date;
		this.viewCount = viewCount;
		this.editCount = editCount;
		this.resourceCount = resourceCount;
		this.memberCount = memberCount;
	}

	public Date getDate() {
		return date;
	}

	public long getViewCount() {
		return viewCount;
	}

	public long getEditCount() {
		return editCount;
	}

	public long getMemberCount() {
		return memberCount;
	}

	public long getResourceCount() {
		return resourceCount;
	}
	
}
