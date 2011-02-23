package org.ocwiki.controller.action.history;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.TestAttempt;
import org.ocwiki.db.dao.HistoryDAO;
import org.ocwiki.taglib.UtilFunctions;

public class ViewAction extends AbstractAction {

	private TestAttempt history;

	@Override
	protected void performImpl() throws IOException, ServletException {
		if (!getParams().hasParameter("id")) {
			throw new ActionException("Bạn cần chọn bài làm muốn xem.");
		}
		history = HistoryDAO.fetchById(getParams().getInt("id"));
		if (!history.getUser().equals(getUser())) {
			throw new ActionException("Bạn không thể xem bài làm của người khác.");
		}
		title("Xem bài làm " + history.getRevision().getName() + " lúc "
				+ UtilFunctions.formatDateTime(history.getTakenDate()));
	}
	
	public TestAttempt getHistory() {
		return history;
	}

}
