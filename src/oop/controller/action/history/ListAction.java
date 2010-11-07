package oop.controller.action.history;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.data.History;
import oop.db.dao.HistoryDAO;

public class ListAction extends AbstractAction {

	private int start;
	private int size;
	private List<History> histories;
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

	public List<History> getHistories() {
		return histories;
	}

	public long getCount() {
		return count;
	}
	
}
