package org.ocwiki.controller.action.history;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.TestAttempt;
import org.ocwiki.db.dao.HistoryDAO;

public class ListAction extends AbstractAction {

	private int start;
	private int size;
	private List<TestAttempt> histories;
	private long count;

	@Override
	protected void performImpl() throws IOException, ServletException {
		start = getParams().getInt("start", 0);
		size = getParams().getInt("size", 20);
		histories = HistoryDAO.fetchByUser(getUser().getId(), start, size);
		count = HistoryDAO.countByUser(getUser().getId());
	}

	public int getStart() {
		return start;
	}

	public int getSize() {
		return size;
	}

	public List<TestAttempt> getHistories() {
		return histories;
	}

	public long getCount() {
		return count;
	}
	
}
